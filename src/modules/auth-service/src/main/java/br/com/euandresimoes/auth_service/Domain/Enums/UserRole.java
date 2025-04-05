package br.com.euandresimoes.auth_service.Domain.Enums;

public enum UserRole {
    PREMIUM("Premium"),
    USER("User");

    private final String role;

    private UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
