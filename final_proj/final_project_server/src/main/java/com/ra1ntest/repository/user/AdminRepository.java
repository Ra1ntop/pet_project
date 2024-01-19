package com.ra1ntest.repository.user;

import com.ra1ntest.persistance.entity.user.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends UserRepository<Admin> {
}
