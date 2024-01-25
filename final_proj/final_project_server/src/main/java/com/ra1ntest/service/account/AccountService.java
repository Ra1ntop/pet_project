package com.ra1ntest.service.account;

import com.ra1ntest.persistance.entity.user.User;

public interface AccountService {
    User getUserInfo(String login);
}
