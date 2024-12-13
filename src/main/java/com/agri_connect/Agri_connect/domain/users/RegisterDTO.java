package com.agri_connect.Agri_connect.domain.users;

import lombok.Getter;
import org.springframework.beans.BeanUtils;

@Getter
public class RegisterDTO {
    private String name;
    private String email;
    private String password;
    private UsersRole role;

    public RegisterDTO(Users usuario) {
        BeanUtils.copyProperties(usuario, this);
    }

    public RegisterDTO() {
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UsersRole getRole() {
        return role;
    }
}
