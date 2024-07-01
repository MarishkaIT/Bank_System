package com.bank.bank_system.entity;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public enum Role implements GrantedAuthority {

    ADMIN,
    USER;

    @Override
    public String getAuthority() {
        return null;
    }
}
