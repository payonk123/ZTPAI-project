package org.example.projekt_ztpai.dto;

public class RegisterRequest {
    private String username;
    private String password;
    private String email;
    private String role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }
}