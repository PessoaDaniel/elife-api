package com.dansales.elife.elifeapi.repository;


import com.dansales.elife.elifeapi.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    public User findByLogin(String userName);

}
