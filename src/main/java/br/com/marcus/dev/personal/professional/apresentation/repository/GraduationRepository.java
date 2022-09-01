package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GraduationRepository extends JpaRepository<Graduation, UUID> {

    @Query("SELECT g FROM Graduation g WHERE g.status = true")
    Page<Graduation> findAll(Pageable page);
}
