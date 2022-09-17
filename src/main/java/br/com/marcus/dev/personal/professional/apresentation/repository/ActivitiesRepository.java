package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activities, UUID> {

    @Query("SELECT a FROM Activities a WHERE a.certificate.id = :id")
    Optional<Activities> findByCertificate(@Param("id") UUID id);

    @Query("SELECT a FROM Activities a WHERE a.hardSkills.id = :id")
    Optional<Activities> findByHardSkills(@Param("id") UUID id);

    @Query("SELECT a FROM Activities a WHERE a.course.id = :id")
    Optional<Activities> findByCourse(@Param("id") UUID id);

    @Query("SELECT a FROM Activities a WHERE a.graduation.id = :id")
    Optional<Activities> findByGraduation(@Param("id") UUID id);
}
