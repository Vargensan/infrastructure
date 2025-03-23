package com.waf.infrastructure.security.context;

import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class AuthenticationToken {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
