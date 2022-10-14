package br.com.marcus.dev.personal.professional.apresentation.services.telephone;

import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import br.com.marcus.dev.personal.professional.apresentation.entities.Telephone;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import br.com.marcus.dev.personal.professional.apresentation.repository.DataPersonalRepository;
import br.com.marcus.dev.personal.professional.apresentation.repository.TelephoneRepository;
import org.hibernate.sql.Delete;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteTelephoneServiceTest {

    @Autowired private DataPersonalRepository dataPersonalRepository;
    @Autowired private TelephoneRepository telephoneRepository;
    @Autowired private DeleteTelephoneService deleteTelephoneService;

    @BeforeEach
    public void setupInit(){
        DataPersonal dataPersonal = new DataPersonal("Marcus", 27, LocalDate.of(1995,4,20),
                MaritalStatus.MARRIED);
        dataPersonalRepository.save(dataPersonal);
        Telephone telephone = new Telephone("55","11","993527709");
        telephone.setDataPersonal(dataPersonal);
        telephone.setId(UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371"));
        telephoneRepository.save(telephone);
    }

    @Test
    @Transactional
    @DisplayName("Apagar Telephone")
    public void deleteTest(){
        // Executando método
        UUID id = UUID.fromString("cb260da4-01fb-48f0-aec4-d7f9db2ff371");
        deleteTelephoneService.delete(id);
        // Testes Unitários
        Optional<Telephone> optionalTelephone = telephoneRepository.findById(id);
        Assertions.assertTrue(optionalTelephone.isEmpty());
    }
}
