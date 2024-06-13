package com.dansales.elife.elifeapi.models.enums;



public enum AuthRole {

    ADMIN("admin"),
    USER("user");

    private final String role;

    AuthRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
