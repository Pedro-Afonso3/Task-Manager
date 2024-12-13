package com.agri_connect.Agri_connect.domain.users;

public enum UsersRole {
    ADMIN("ROLE_ADMIN"),
    AGRICULTOR("ROLE_AGRICULTOR"),
    TRANSPORTADOR_INDIVIDUAL("ROLE_TRANSPORTADOR_INDIVIDUAL"),
    TRANSPORTADOR_EMPRESA("ROLE_TRANSPORTADOR_EMPRESA");

    private String role;

    UsersRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
