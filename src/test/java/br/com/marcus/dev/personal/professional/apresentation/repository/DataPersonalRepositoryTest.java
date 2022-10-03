package br.com.marcus.dev.personal.professional.apresentation.repository;

import br.com.marcus.dev.personal.professional.apresentation.entities.DataPersonal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class DataPersonalRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private DataPersonalRepository dataPersonalRepository;

    @BeforeEach
    public void setupInit(){
        DataPersonal dataPersonal1 = new DataPersonal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        DataPersonal dataPersonal2 = new DataPersonal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff702"));
        dataPersonal2.setStatus(false);
        testEntityManager.persist(dataPersonal1);
        testEntityManager.persist(dataPersonal2);
    }

    @Test
    @DisplayName("Deve conter pelo menos um DataPersonal")
    public void findAllTest(){
        Page<DataPersonal> page = dataPersonalRepository.findAll(PageRequest.of(1,1));
        Assertions.assertThat(page.getSize() == 1).isEqualTo(true);
    }

    @Test
    @DisplayName("Verificar se tem os dados pessoais cadastrados")
    public void checkHaveDataPersonalTest(){
        List<DataPersonal> page = dataPersonalRepository.checkHaveDataPersonal(UUID.fromString("bb260da4-01fb-48f0-aec4-d7f9db2ff701"));
        Assertions.assertThat(page.size() == 0).isEqualTo(true);
    }
}
