package com.luxfacta.desafio.services;

import com.luxfacta.desafio.security.UserSS;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserSevice {

    public static UserSS authenticated() {
        try {
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
