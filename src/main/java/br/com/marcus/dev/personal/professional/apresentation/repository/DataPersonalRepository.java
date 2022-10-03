package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DataPersonalRepository extends JpaRepository<DataPersonal, UUID> {

    @Query("SELECT dt FROM DataPersonal dt WHERE dt.status = true")
    Page<DataPersonal> findAll(Pageable page);

    @Query("SELECT dt FROM DataPersonal dt WHERE dt.status = true AND dt.user.uuid = :id")
    List<DataPersonal> checkHaveDataPersonal(@Param("id") UUID id);
}
