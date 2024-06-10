package com.dansales.elife.elifeapi.DTO;

import com.dansales.elife.elifeapi.models.AuthRole;

public record UserDTO(
        String email, String name, String password,
        String login, String cpf, String phone,
        String rg
        ) {
}
