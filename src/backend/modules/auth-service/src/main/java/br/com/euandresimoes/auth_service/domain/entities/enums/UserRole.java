package br.com.euandresimoes.auth_service.domain.entities.enums;

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
