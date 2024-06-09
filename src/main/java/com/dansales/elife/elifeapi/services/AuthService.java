package com.dansales.elife.elifeapi.services;


import com.dansales.elife.elifeapi.models.User;
import com.dansales.elife.elifeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return null;
    }
}
