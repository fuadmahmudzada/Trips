package com.trips.mvc.services;

import com.trips.mvc.dtos.ContactDto;
import com.trips.mvc.dtos.SubscribeDto;
import com.trips.mvc.models.SubscribeUsers;

import java.util.List;

public interface EmailService {
    void sendMessage(ContactDto contactDto);
    void subscribe(SubscribeDto subscribeDto);
    void sendConfirmationEmail(String email, String token);
    void sendDiscountMessage(List<SubscribeUsers> subscribeUsersList, int price1, int price2);
}
