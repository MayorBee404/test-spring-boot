package com.abistudio.testspringboot.user;

public class LoginResponse {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccesToken() {
        return accesToken;
    }

    public void setAccesToken(String accesToken) {
        this.accesToken = accesToken;
    }

    private String accesToken;

    public LoginResponse(){

    }
    public LoginResponse(String username, String accesToken){
        this.username = username;
        this.accesToken = accesToken;
    }
}
