package br.com.marcus.dev.personal.professiona.apresentaion.repository;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TelephoneRepository extends JpaRepository<Telephone, UUID> {
}