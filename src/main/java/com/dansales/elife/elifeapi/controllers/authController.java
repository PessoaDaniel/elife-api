package com.dansales.elife.elifeapi.controllers;

import com.dansales.elife.elifeapi.DTO.AuthDTO;
import com.dansales.elife.elifeapi.DTO.TokenDTO;
import com.dansales.elife.elifeapi.DTO.UserDTO;
import com.dansales.elife.elifeapi.models.AuthRole;
import com.dansales.elife.elifeapi.models.User;
import com.dansales.elife.elifeapi.repository.UserRepository;
import com.dansales.elife.elifeapi.services.AccessTokenService;
import com.dansales.elife.elifeapi.services.AuthService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@CrossOrigin("http://localhost:4200")
public class authController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final AccessTokenService accessTokenService;


    public authController(AuthenticationManager authenticationManager, UserRepository userRepository, AccessTokenService accessTokenService, AuthService authService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.accessTokenService = accessTokenService;

    }

    @PostMapping(
            path = "auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity signIn(@RequestBody @Validated AuthDTO loginData) {
        UsernamePasswordAuthenticationToken loginPass = new UsernamePasswordAuthenticationToken(loginData.login(), loginData.password());
        Authentication auth = this.authenticationManager.authenticate(loginPass);
            String token = this.accessTokenService.generateAccessToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new TokenDTO(token));
    }
    @PostMapping(
            path = "auth/create-default",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity register(@RequestBody @Validated UserDTO userDTO) throws Exception {
        if (this.userRepository.findByLogin(userDTO.login()) != null) {
            return ResponseEntity.badRequest().build();
        }
        User newUser = new User();

        newUser.setLogin(userDTO.login());
        newUser.setPassword(new BCryptPasswordEncoder().encode(userDTO.password()));
        newUser.setRg(userDTO.rg());
        newUser.setName(userDTO.name());
        newUser.setCpf(userDTO.cpf());
        newUser.setEmail(userDTO.email());
        newUser.setPhone(userDTO.phone());
        newUser.setRole(AuthRole.USER);

        userRepository.save(newUser);
        return ResponseEntity.ok().build();
    }
}
