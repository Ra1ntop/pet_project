package com.ra1ntest.repository.token;

import com.ra1ntest.persistance.entity.token.Token;
import com.ra1ntest.persistance.entity.user.User;
import com.ra1ntest.repository.BaseRepository;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends BaseRepository<Token> {

    Optional<Token> findAllByToken(String token);

    List<Token> findAllByUserId(Long id);

}
