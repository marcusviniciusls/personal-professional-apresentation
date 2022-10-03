package br.com.marcus.dev.personal.professional.apresentation.services.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
public class VerifyLocalDateServiceTest {

    private VerifyLocalDateService verifyLocalDateService = new VerifyLocalDateService();

    @Test
    public void dateInitBeforeDateFinishTrue(){
        LocalDate dateInit = LocalDate.of(2020, 9, 12);
        LocalDate dateFinish = LocalDate.of(2021, 9, 12);
        boolean verify = verifyLocalDateService.dateInitBeforeDateFinish(dateInit, dateFinish);
        Assertions.assertTrue(verify);
    }

    @Test
    public void dateInitBeforeDateFinishFalse(){
        LocalDate dateInit = LocalDate.of(2020, 9, 12);
        LocalDate dateFinish = LocalDate.of(2019, 9, 12);
        boolean verify = verifyLocalDateService.dateInitBeforeDateFinish(dateInit, dateFinish);
        Assertions.assertFalse(verify);
    }
}
