package br.com.euandresimoes.user_service.domain.entity;

import br.com.euandresimoes.user_service.domain.entity.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "users_tb")
public class UserEntity {

    @Id
    private Long id;
    private String username;
    private String display_name;
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public UserEntity() {
    }

    public UserEntity(String username, String display_name, String email, UserRole role) {
        this.username = username;
        this.display_name = display_name;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }
}
