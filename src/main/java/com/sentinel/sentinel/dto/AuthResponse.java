package com.sentinel.sentinel.dto;

public class AuthResponse {

    private boolean success;
    private String message;
    private String token;
    private String username;
    private String role;

    public AuthResponse() {
    }

    public AuthResponse(boolean success,
                        String message,
                        String token,
                        String username,
                        String role) {

        this.success = success;
        this.message = message;
        this.token = token;
        this.username = username;
        this.role = role;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}