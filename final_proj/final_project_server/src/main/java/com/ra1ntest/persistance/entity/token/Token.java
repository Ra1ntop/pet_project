package com.ra1ntest.persistance.entity.token;

import com.ra1ntest.persistance.entity.BaseEntity;
import com.ra1ntest.persistance.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tokens")
public class Token extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String token;

    @Column(nullable = false)
    public Boolean expired;

    @ManyToOne
    public User user;

    public Token() {
        this.expired = false;
    }
}
