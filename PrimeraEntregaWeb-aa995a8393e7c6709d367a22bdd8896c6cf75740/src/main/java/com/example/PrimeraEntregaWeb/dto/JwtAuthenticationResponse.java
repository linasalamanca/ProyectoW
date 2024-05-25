package com.example.PrimeraEntregaWeb.dto;


public class JwtAuthenticationResponse {
    private String token;
    private Long id;
    private String user;
    private String role;

    public JwtAuthenticationResponse() {
    }

    public JwtAuthenticationResponse(String token,Long id, String user, String role) {
        this.token = token;
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
