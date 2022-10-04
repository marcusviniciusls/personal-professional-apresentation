package br.com.marcus.dev.personal.professional.apresentation.services.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class GetKeyFileTest {

    private GetKeyFile getKeyFile = new GetKeyFile();

    @Test
    @DisplayName("Deve retornar a palavra Java")
    public void getKeyFileTest(){
        String fileName = "/teste/caminho/java";
        String returnTest = getKeyFile.getKeyFile(fileName);
        Assertions.assertEquals("java", returnTest);
    }
}
