package br.com.euandresimoes.auth_service.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.euandresimoes.auth_service.domain.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
}
