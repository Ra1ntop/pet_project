package com.ra1ntest.repository.user;

import com.ra1ntest.persistance.entity.user.User;
import com.ra1ntest.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository<U extends User> extends BaseRepository<U> {

    Optional<U> findByLogin(String login);

    boolean existsByLogin(String login);

}
