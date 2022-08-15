package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BranchActivityRepository extends JpaRepository<BranchActivity, UUID> {

    @Query("SELECT ba FROM BranchActivity ba WHERE ba.status = true")
    Page<BranchActivity> findAll(Pageable page);
}
