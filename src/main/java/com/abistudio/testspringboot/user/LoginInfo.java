package com.abistudio.testspringboot.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginInfo {
    @NotNull
    @Length(min = 5, max = 50)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    @Length(min = 8, max = 16)
    private String password;
}
