package com.ra1ntest.persistance.entity.user;

import com.ra1ntest.persistance.type.RoleType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {
    public Admin() {
        super();
        setRoleType(RoleType.ADMIN);
    }
}
