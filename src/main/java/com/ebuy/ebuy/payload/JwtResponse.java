package com.ebuy.ebuy.payload;

public class JwtResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }
}
