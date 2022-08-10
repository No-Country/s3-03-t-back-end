package com.s3.t.util;

public enum RolesEnum {
    USER("USER"),
    ROOMIE("ROOMIE"),
    TENAT("TENAT");

    private String ROLE_PREFIX ="ROLE_";
    private final String name ;

    RolesEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getFullRoleName()
    {
        return ROLE_PREFIX + name;

    }
}