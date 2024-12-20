package com.havrem.restaurantreserverbackend.services;

import com.havrem.restaurantreserverbackend.models.*;
import com.havrem.restaurantreserverbackend.other.*;
import com.havrem.restaurantreserverbackend.repositories.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService implements Observable {
    private final List<Observer> observers;
    private final RestaurantRepository restaurantRepository;
    private final NotificationService notificationService;
    private NotificationFactory notificationFactory;
    private EmailService emailService;

    public RestaurantService(RestaurantRepository restaurantRepository, NotificationService notificationService, NotificationFactory notificationFactory, EmailService emailService) {
        this.restaurantRepository = restaurantRepository;
        this.notificationService = notificationService;
        this.notificationFactory = notificationFactory;
        this.emailService = emailService;
        observers = new ArrayList<>();
    }

    public List<Restaurant> getRestaurantsByPreference(Preference preference) {
        return restaurantRepository.findByPreference(preference);
    }

    public void reserveTable(long id, String email) {
        Optional<Restaurant> res1 = restaurantRepository.findById(id);

        if (res1.isPresent()) {
            //Create reservation
            Restaurant restaurant = res1.get();
            System.out.println("Found restuarant: " + restaurant.getName());

            Optional<Timeslot> res2 = restaurant.getTimeslots().stream().filter(ts-> ts.getStatus().equals(Status.AVAILABLE)).findFirst();

            if (res2.isPresent()) {
                Timeslot timeslot = res2.get();
                System.out.println("Found timeslot: " + timeslot.getStart());
                timeslot.setStatus(Status.UNAVAILABLE);

                Reservation reservation = new Reservation(timeslot, email);
                System.out.println("Reservation: " + reservation);
                restaurant.getReservations().add(reservation);
                restaurantRepository.save(restaurant);
                System.out.println("Saved restaurant");

                //Send confirmation email
                EmailNotification emailNotification = notificationFactory.createEmailNotification(email, "Reservation confirmation", String.format("You have booked a table at \"%s\" between %s-%s %s/%s.", restaurant.getName(), timeslot.getStart().getHour(),timeslot.getEnd().getHour(), timeslot.getStart().getDayOfMonth(), timeslot.getStart().getMonthValue()));
                System.out.println("Made emailNotification");

                emailService.sendSimpleMail(emailNotification.getEmailDetails());
                System.out.println("Send mail");
            }
        }
    }

    public void addTable(long id) {
        Optional<Restaurant> result = restaurantRepository.findById(id);

        if (result.isPresent()) {
            Restaurant restaurant = result.get();
            restaurant.setTables(restaurant.getTables() + 1);
            restaurantRepository.save(restaurant);

            notificationService.update(id); //Notify observer
        }
    }

    public void addSmsNotification(long id, String number) {
        Optional<Restaurant> result = restaurantRepository.findById(id);

        if (result.isPresent()) {
            Restaurant restaurant = result.get();
            String text = String.format("A table has become available at %s.", restaurant.getName());

            SmsNotification smsNotification = notificationFactory.createSmsNotification(number, text);
            notificationService.saveSmsNotification(smsNotification);

            restaurant.addToWaitlist(smsNotification);
        }
    }

    public void addEmailNotification(long id, String number) {
        Optional<Restaurant> result = restaurantRepository.findById(id);

        if (result.isPresent()) {
            Restaurant restaurant = result.get();
            String subject = "Table Available";
            String body = String.format("A table has become available at %s.", restaurant.getName());

            EmailNotification emailNotification = notificationFactory.createEmailNotification(number, subject, body);
            notificationService.saveEmailNotification(emailNotification);

            restaurant.addToWaitlist(emailNotification);
        }
    }

    @Override
    public void addObserver(Observer observer) {

    }

    @Override
    public void removeObserver(Observer observer) {

    }

    @Override
    public void notifyObservers(long id) {
        for (Observer observer : observers) {
            observer.update(id);
        }
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public void saveRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
    }
}
