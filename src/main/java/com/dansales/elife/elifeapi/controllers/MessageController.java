package com.dansales.elife.elifeapi.controllers;


import com.dansales.elife.elifeapi.services.mensage.EmailService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final EmailService emailService;

    public MessageController(EmailService emailService) {
        this.emailService = emailService;
    }
}
