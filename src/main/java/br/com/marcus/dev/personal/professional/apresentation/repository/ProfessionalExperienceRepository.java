package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.ProfessionalExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProfessionalExperienceRepository extends JpaRepository<ProfessionalExperience, UUID> {

    @Query("SELECT pe FROM ProfessionalExperience pe WHERE pe.status = true AND pe.statusWork = 0")
    List<ProfessionalExperience> findAll();

    @Query("SELECT pe FROM ProfessionalExperience pe WHERE pe.status = true ORDER BY pe.dateInit DESC, pe.dateFinish ASC")
    List<ProfessionalExperience> findAllEntity();
}
