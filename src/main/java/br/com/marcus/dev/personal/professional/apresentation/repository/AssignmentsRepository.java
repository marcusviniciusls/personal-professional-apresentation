package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.Assignments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AssignmentsRepository extends JpaRepository<Assignments, UUID> {

    @Query("SELECT a FROM Assignments a WHERE a.status = true AND a.professionalExperience.id = :id")
    Page<Assignments> findAll(Pageable page, @Param("id") UUID id);
}
