package br.com.euandresimoes.auth_service.Domain.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.euandresimoes.auth_service.Domain.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
}
