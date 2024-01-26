package com.ra1ntest.api.dto.response.account;

import com.ra1ntest.persistance.entity.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomersDto {
    private Long accountId;
    private String login;
    private String firstName;
    private String lastName;
    private String age;
    private String roleType;
    private String country;
    private Boolean accountNonLocked;
    private String description;
    private String blockedAt;


    public CustomersDto(User user) {
        setAccountId(user.getId());
        setLogin(user.getLogin());
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setAge(user.getAge());
        setCountry(user.getCountry());
        setRoleType(user.getRoleType().name());
        setAccountNonLocked(user.getAccountNonLocked());
        setDescription(user.getDescription());
        setBlockedAt(user.getBlockedAt());

    }

}
