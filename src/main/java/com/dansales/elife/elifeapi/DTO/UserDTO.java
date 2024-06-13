package com.dansales.elife.elifeapi.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String login;
    @NotBlank
    private String cpf;
    @NotBlank
    private String phone;
    @NotBlank
    private String rg;
}
