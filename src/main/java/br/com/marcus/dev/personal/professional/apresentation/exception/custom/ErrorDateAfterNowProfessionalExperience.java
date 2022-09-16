package br.com.marcus.dev.personal.professional.apresentation.exception.custom;

public class ErrorDateAfterNowProfessionalExperience extends RuntimeException{

    public ErrorDateAfterNowProfessionalExperience(String message) {
        super(message);
    }
}
