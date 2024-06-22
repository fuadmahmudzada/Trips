package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.ContactDto;
import com.trips.mvc.dtos.SubscribeDto;
import com.trips.mvc.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMessage(ContactDto contactDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(contactDto.getEmail());
        message.setTo("nathen.quigley@ethereal.email");
        message.setSubject("User Message");

//        String res = "http://localhost:5050/contact/send?email="+email;
        message.setText(contactDto.getMessage());
        mailSender.send(message);
    }

    @Override
    public void subscribe(SubscribeDto subscribeDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nathen.quigley@ethereal.email");
            message.setTo(subscribeDto.getEmail());
        message.setSubject("User Subscription");
        message.setText("You have subscribed our daily newsletter");
        mailSender.send(message);
    }
}
