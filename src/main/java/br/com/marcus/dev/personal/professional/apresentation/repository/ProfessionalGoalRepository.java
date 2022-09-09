package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalGoal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfessionalGoalRepository extends JpaRepository<ProfessionalGoal, UUID> {

    @Query("SELECT pg FROM ProfessionalGoal pg WHERE pg.status = true ORDER BY pg.dateIssue desc")
    Page<ProfessionalGoal> findAll(Pageable page);
}
