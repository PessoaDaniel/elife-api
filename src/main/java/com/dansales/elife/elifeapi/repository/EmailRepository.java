package com.dansales.elife.elifeapi.repository;

import com.dansales.elife.elifeapi.models.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, String> {
}
