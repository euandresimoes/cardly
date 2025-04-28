package br.com.euandresimoes.user_service.domain.entity.enums;

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
