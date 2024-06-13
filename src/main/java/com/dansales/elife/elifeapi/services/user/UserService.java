package com.dansales.elife.elifeapi.services.user;

import com.dansales.elife.elifeapi.models.enums.AuthRole;
import com.dansales.elife.elifeapi.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserService {
    private final CrudRepository<User, String> repository;

    public UserService (CrudRepository<User, String> repository) {
            this.repository = repository;
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

        this.repository.save(newUser);
    }
}
