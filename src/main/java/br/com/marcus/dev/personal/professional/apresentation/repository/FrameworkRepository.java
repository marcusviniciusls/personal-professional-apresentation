package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FrameworkRepository extends JpaRepository<Framework, UUID> {

    @Query("SELECT f FROM Framework f WHERE f.status = true")
    Page<Framework> findAll(Pageable page);
}
