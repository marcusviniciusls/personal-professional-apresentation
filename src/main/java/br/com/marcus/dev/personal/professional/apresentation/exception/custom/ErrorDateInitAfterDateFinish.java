package br.com.marcus.dev.personal.professional.apresentation.exception.custom;

public class ErrorDateInitAfterDateFinish extends RuntimeException{

    public ErrorDateInitAfterDateFinish(String message) {
        super(message);
    }
}
