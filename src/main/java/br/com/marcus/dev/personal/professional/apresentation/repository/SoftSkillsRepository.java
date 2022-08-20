package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.SoftSkills;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SoftSkillsRepository extends JpaRepository<SoftSkills, UUID> {

    @Query("SELECT s FROM SoftSkills s WHERE s.status = true")
    Page<SoftSkills> findAll(Pageable page);
}
