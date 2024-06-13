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

    @PostMapping(path = "/service/send-verify-email",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendVerificationEmail(@RequestBody @Valid TwoFactorEmailDTO emailDTO) {
        Email email = new Email();
        BeanUtils.copyProperties(emailDTO, email);
        this.emailService.send2FactorEmail(email);
        return ResponseEntity.ok().build();
    }

}
