package com.ra1ntest.persistance.entity.user;

import com.ra1ntest.persistance.type.RoleType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CUSTOMER")
public class Customer extends User {

    public Customer() {
        super();
        setRoleType(RoleType.CUSTOMER);
    }
}
