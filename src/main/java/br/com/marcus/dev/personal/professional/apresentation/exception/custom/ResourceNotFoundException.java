package br.com.marcus.dev.personal.professional.apresentation.exception.custom;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
