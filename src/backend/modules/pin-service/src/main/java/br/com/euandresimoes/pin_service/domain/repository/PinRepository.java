package br.com.euandresimoes.pin_service.domain.repository;

import br.com.euandresimoes.pin_service.domain.entity.PinEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PinRepository extends JpaRepository<PinEntity, Long> {
    List<PinEntity> findAllByPinCreatorUsername(String username);
}
