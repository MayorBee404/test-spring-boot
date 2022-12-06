package com.abistudio.testspringboot.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RegistrationInfo {

    @NotNull
    @Length(min = 5,max = 16)
    @Pattern(regexp = "^[a-zA-Z0-9]+$",message = "Format Salah")
    private String username;

    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*\\u0021|.*\\u0040|.*\\u0023|.*\\u005F)(.*)$", message = "Format Salah")
    @Length(min = 8, max = 16)
    private String password;

    @Nullable
    @Length(max = 50)
    private String name;

    @Nullable
    @Length(max = 50)
    private String address;

    public RegistrationInfo(){
    }

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

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    @Nullable
    public String getAddress() {
        return address;
    }

    public void setAddress(@Nullable String address) {
        this.address = address;
    }

    public RegistrationInfo(String username, String password, @Nullable String name, @Nullable String address){
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
    }

}
