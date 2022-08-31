package br.com.marcus.dev.personal.professional.apresentation.services.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class VerifyLocalDateService {

    public boolean dateInitBeforeDateFinish(LocalDate dateInit, LocalDate dateFinish){
        if (dateInit.isBefore(dateFinish)){
            return true;
        }
        return false;
    }
}
