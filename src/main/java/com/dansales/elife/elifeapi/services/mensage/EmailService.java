package com.dansales.elife.elifeapi.services.mensage;

import com.dansales.elife.elifeapi.models.Email;
import com.dansales.elife.elifeapi.models.enums.StatusEmail;
import com.dansales.elife.elifeapi.repository.EmailRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final EmailRepository emailRepository;

    private final JavaMailSender mailSender;

    public EmailService(EmailRepository emailRepository, JavaMailSender mailSender) {
        this.emailRepository = emailRepository;
        this.mailSender = mailSender;
    }
    public void  send2FactorEmail(Email email) {
        email.setContent("Este é um email de confirmação, click no link para ativar sua conta");
        email.setFrom("elife@gmail.com");
        email.setTo(email.getTo());
        email.setSubject("Verificação de conta elife");
        email.setOwner("Elife");
        this.sendEmail(email);
    }

    public void sendEmail(Email email) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.getTo());
            message.setSubject(email.getSubject());
            message.setText(email.getContent());
            email.setStatus(StatusEmail.SENT);
            this.mailSender.send(message);
        } catch (MailException exception) {
            email.setStatus(StatusEmail.ERROR);
        } finally {
            this.emailRepository.save(email);
        }


    }
}
