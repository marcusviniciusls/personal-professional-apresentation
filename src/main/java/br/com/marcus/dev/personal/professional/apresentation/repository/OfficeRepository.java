package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.Office;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OfficeRepository extends JpaRepository<Office, UUID> {
}