package com.dansales.elife.elifeapi.controllers;

import com.dansales.elife.elifeapi.DTO.AuthDTO;
import com.dansales.elife.elifeapi.DTO.TokenDTO;
import com.dansales.elife.elifeapi.DTO.UserDTO;
import com.dansales.elife.elifeapi.models.User;
import com.dansales.elife.elifeapi.repository.UserRepository;
import com.dansales.elife.elifeapi.services.auth.AccessTokenService;
import com.dansales.elife.elifeapi.services.auth.AuthService;
import com.dansales.elife.elifeapi.services.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@CrossOrigin("*")
public class authController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AccessTokenService accessTokenService;
    private final UserService userService;

    public authController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            AccessTokenService accessTokenService,
            AuthService authService,
            UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.accessTokenService = accessTokenService;
        this.userService = userService;
    }

    @PostMapping(
            path = "auth/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity signIn(@RequestBody @Valid AuthDTO loginData) {
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
    public ResponseEntity register(@RequestBody @Valid UserDTO userDTO) throws Exception {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        if (this.userRepository.findByLogin(user.getLogin()) != null) {
            return ResponseEntity.badRequest().build();
        }
        this.userService.StoreDefault(user);
        return ResponseEntity.ok().build();
    }
}
