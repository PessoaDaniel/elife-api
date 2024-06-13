package com.dansales.elife.elifeapi.controllers;


import com.dansales.elife.elifeapi.DTO.TwoFactorEmailDTO;
import com.dansales.elife.elifeapi.models.Email;
import com.dansales.elife.elifeapi.services.mensage.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final EmailService emailService;

    public MessageController(EmailService emailService) {
        this.emailService = emailService;
    }
}
