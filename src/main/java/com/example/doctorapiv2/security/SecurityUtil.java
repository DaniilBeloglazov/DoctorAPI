package com.example.doctorapiv2.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String getCurrentUserLogin() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
    }
}
