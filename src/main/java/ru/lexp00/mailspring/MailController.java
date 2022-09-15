package ru.lexp00.mailspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/mail")
public class MailController {

    private MailService mailService;

    @Autowired
    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    @PostMapping("/send")
    private void send(@RequestParam String email) {
        mailService.sendMessage(email);
    }

    @PostMapping("/html")
    private void sendHtml(@RequestParam String email) throws MessagingException, UnsupportedEncodingException {
        mailService.sendHtmlMessage(email);
    }
}
