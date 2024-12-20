package com.havrem.restaurantreserverbackend.services;

import com.havrem.restaurantreserverbackend.models.SmsDetails;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${TWILIO_ACCOUNT_SID}")
    String ACCOUNT_SID;

    @Value("${TWILIO_AUTH_TOKEN}")
    String AUTH_TOKEN;

    @Value("${TWILIO_OUTGOING_SMS_NUMBER}")
    String OUTGOING_SMS_NUMBER;

    public void sendSms(SmsDetails smsDetails) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber(smsDetails.getPhoneNumber()),
                new PhoneNumber(OUTGOING_SMS_NUMBER),
                smsDetails.getMessage())
                .create();
    }
}
