package br.com.marcus.dev.personal.professiona.apresentaion.repository;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmailRepository extends JpaRepository<Email, UUID> {
}
