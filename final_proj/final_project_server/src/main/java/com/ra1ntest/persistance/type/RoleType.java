package com.ra1ntest.persistance.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    CUSTOMER(Set.of(
            PermissionType.CUSTOMER_CREATE,
            PermissionType.CUSTOMER_READ,
            PermissionType.CUSTOMER_UPDATE,
            PermissionType.CUSTOMER_DELETE
    )),
    ADMIN(Set.of(
            PermissionType.ADMIN_CREATE,
            PermissionType.ADMIN_READ,
            PermissionType.ADMIN_UPDATE,
            PermissionType.ADMIN_DELETE
    ));

    private final Set<PermissionType> permissions;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }

    ;


}
