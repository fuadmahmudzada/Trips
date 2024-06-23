package com.trips.mvc.services;

import com.trips.mvc.dtos.ContactDto;
import com.trips.mvc.dtos.SubscribeDto;

public interface EmailService {
    void sendMessage(ContactDto contactDto);
    void subscribe(SubscribeDto subscribeDto);
}
