package com.havrem.restaurantreserverbackend.other;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(long id);
}
