package br.com.marcus.dev.personal.professional.apresentation.services.telephone;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DeleteTelephoneServiceTest {

    @BeforeEach
    public void setupInit(){

    }

    @Test
    @Transactional
    @DisplayName("Apagar Subject")
    public void deleteTest(){

    }
}
