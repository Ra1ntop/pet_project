package com.ra1ntest.util;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtil {

    public SecurityUtil() {
    }

    public static boolean isAuthenticated() {
        return getAuthentication() != null;
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static void setAu(UsernamePasswordAuthenticationToken updateToken) {
        SecurityContextHolder.getContext().setAuthentication(updateToken);
    }
}
