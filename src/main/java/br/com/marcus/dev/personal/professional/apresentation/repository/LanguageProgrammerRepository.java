package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LanguageProgrammerRepository extends JpaRepository<LanguageProgrammer, UUID> {

    @Query("SELECT lp FROM LanguageProgrammer lp WHERE lp.status = true")
    Page<LanguageProgrammer> findAll(Pageable page);
}
