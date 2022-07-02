package br.com.marcus.dev.personal.professiona.apresentaion.repository;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MaterialRepository extends JpaRepository<Material, UUID> {
}
