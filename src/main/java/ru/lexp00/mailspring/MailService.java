package ru.lexp00.mailspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

@Service
public class MailService {

    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.sender.text}")
    private String text;



    @Autowired
    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMessage(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(username);
        message.setTo(email);
        message.setSubject("Text mail");
        message.setText("Hello. This message from " + email);
        mailSender.send(message);
    }
    
    public void sendHtmlMessage(String email) throws MessagingException, UnsupportedEncodingException {
        var message = mailSender.createMimeMessage();
        var helper = new MimeMessageHelper(message);
        helper.setFrom(new InternetAddress(username, text));
        helper.setTo(email);
        helper.setSubject("Message");
        helper.setText("<h1> Message text <b>HTML </b>. <br> Second </h1", true);
        mailSender.send(message);
    }
}
