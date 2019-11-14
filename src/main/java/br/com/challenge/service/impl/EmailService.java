package br.com.challenge.service.impl;

import br.com.challenge.entity.Users;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public interface EmailService {


    void sendOrderConfimationEmail(Users users);

    void sendEmail(SimpleMailMessage msg);

    void sendNewPasswordEmail(Users users, String newPass);
}
