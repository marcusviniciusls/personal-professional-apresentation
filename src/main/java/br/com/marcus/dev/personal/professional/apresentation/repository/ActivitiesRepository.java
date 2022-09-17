package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.Activities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ActivitiesRepository extends JpaRepository<Activities, UUID> {

    @Query("SELECT a FROM Activities a WHERE a.status = true AND a.certificate.id = :id")
    Optional<Activities> findByCertificate(@Param("id") UUID id);

    @Query("SELECT a FROM Activities a WHERE a.status = true AND a.hardSkills.id = :id")
    Optional<Activities> findByHardSkills(@Param("id") UUID id);

    @Query("SELECT a FROM Activities a WHERE a.status = true AND a.course.id = :id")
    Optional<Activities> findByCourse(@Param("id") UUID id);

    @Query("SELECT a FROM Activities a WHERE a.status = true AND a.graduation.id = :id")
    Optional<Activities> findByGraduation(@Param("id") UUID id);

    @Query("SELECT a FROM Activities a WHERE a.status = true AND a.professionalExperience.id = :id")
    Optional<Activities> findByProfessionalExperience(@Param("id") UUID id);

    @Query("SELECT a FROM Activities a WHERE a.status = true AND a.professionalGoal.id = :id")
    Optional<Activities> findByProfessionalGoal(@Param("id") UUID id);

    @Query("SELECT a FROM Activities a WHERE a.status = true AND a.softSkills.id = :id")
    Optional<Activities> findBySoftSkills(@Param("id") UUID id);

    @Query("SELECT a FROM Activities a WHERE a.status = true ORDER BY a.date DESC")
    List<Activities> findAll();
}
