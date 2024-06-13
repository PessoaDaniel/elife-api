package com.dansales.elife.elifeapi.services.user;

import com.dansales.elife.elifeapi.models.Email;
import com.dansales.elife.elifeapi.models.VerificationToken;
import com.dansales.elife.elifeapi.models.enums.AuthRole;
import com.dansales.elife.elifeapi.models.User;
import com.dansales.elife.elifeapi.repository.UserRepository;
import com.dansales.elife.elifeapi.repository.VerificationTokenRepository;
import com.dansales.elife.elifeapi.services.auth.AccessTokenService;
import com.dansales.elife.elifeapi.services.mensage.EmailService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class UserService {
    private UserRepository userRepository;
    private VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;

    public UserService (UserRepository userRepository,
                        VerificationTokenRepository verificationTokenRepository, EmailService emailService) {
            this.userRepository = userRepository;
            this.verificationTokenRepository = verificationTokenRepository;
        this.emailService = emailService;
    }
    public void StoreDefault (User user) {
        User newUser = new User();
        newUser.setLogin(user.getLogin());
        newUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        newUser.setRg(user.getRg());
        newUser.setName(user.getName());
        newUser.setCpf(user.getCpf());
        newUser.setEmail(user.getEmail());
        newUser.setPhone(user.getPhone());
        newUser.setRole(AuthRole.USER);
        this.userRepository.save(newUser);

        AccessTokenService  accessTokenService = new AccessTokenService();
        String twoFactorToken  = accessTokenService.generateTwoFactorToken(newUser);
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(twoFactorToken);
        verificationToken.setExpireDate(LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00")));
        this.verificationTokenRepository.save(verificationToken);

        Email email = new Email();
        email.setTo(newUser.getEmail());

        this.emailService.send2FactorEmail(email, verificationToken.getToken());
    }
}
