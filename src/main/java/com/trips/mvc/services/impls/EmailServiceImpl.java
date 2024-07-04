package com.trips.mvc.services.impls;

import com.trips.mvc.dtos.ContactDto;
import com.trips.mvc.dtos.SubscribeDto;
import com.trips.mvc.models.SubscribeUsers;
import com.trips.mvc.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    //QUALIFIER
    //QUALIFIER
    private JavaMailSender mailSender;

    @Override
    public void sendMessage(ContactDto contactDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(contactDto.getEmail());
        message.setTo("nathen.quigley@ethereal.email");
        message.setSubject("UserEntity Message");

//        String res = "http://localhost:5050/contact/send?email="+email;
        message.setText(contactDto.getMessage());
        mailSender.send(message);
    }

    @Override
    public void subscribe(SubscribeDto subscribeDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nathen.quigley@ethereal.email");
            message.setTo(subscribeDto.getEmail());
        message.setSubject("UserEntity Subscription");
        message.setText("You have subscribed our daily newsletter");
        mailSender.send(message);
    }

    @Override
    public void sendConfirmationEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("rachel.von22@ethereal.email");
        message.setTo("rachel.von22@ethereal.email");
        message.setSubject("Confirm email");
        // http://localhost:9090/auth/confrim?email=rizvan@itbrain.edu.az&token=adfhaskjfhaj
        // Template literals
        String res = "http://localhost:5050/auth/confrim?email="+email+"&token="+token;
        message.setText(res);
        mailSender.send(message);
    }

    @Override
    public void sendDiscountMessage(List<SubscribeUsers> subscribeUsersList, int price1,int price2) {
        SimpleMailMessage message = new SimpleMailMessage();
        for(SubscribeUsers users : subscribeUsersList){
       message.setTo(users.getEmail());
            message.setTo("nathen.quigley@ethereal.email");
            message.setSubject("Discount");

//        String res = "http://localhost:5050/contact/send?email="+email;
            message.setText("Price has dropped from " + price2 + "TO" + price1);
            mailSender.send(message);
        }

    }
}
