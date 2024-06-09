package com.dansales.elife.elifeapi.controllers;


import com.dansales.elife.elifeapi.DTO.AuthDTO;
import com.dansales.elife.elifeapi.models.User;
import com.dansales.elife.elifeapi.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class authController {

    @Autowired()
    private AuthenticationManager authenticationManager;

    @PostMapping(
            path = "auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @CrossOrigin(origins = "*")
    public ResponseEntity signIn(@RequestBody @Validated AuthDTO loginData) throws Exception {
            UsernamePasswordAuthenticationToken loginPass = new UsernamePasswordAuthenticationToken(loginData.login(), loginData.password());
            Authentication auth = authenticationManager.authenticate(loginPass);

        return ResponseEntity.ok().build();
    }
}
