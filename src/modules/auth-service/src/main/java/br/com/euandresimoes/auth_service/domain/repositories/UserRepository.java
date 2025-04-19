package br.com.euandresimoes.auth_service.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.euandresimoes.auth_service.domain.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
}
