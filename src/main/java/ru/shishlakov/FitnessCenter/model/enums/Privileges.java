package ru.shishlakov.FitnessCenter.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Privileges implements GrantedAuthority {
    ADMIN,
    USER;


    @Override
    public String getAuthority() {
        return name();
    }
}
