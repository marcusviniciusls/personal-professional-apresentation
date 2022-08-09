package br.com.marcus.dev.personal.professional.apresentation.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Profile {

    ADMIN(0, "ROLE_ADMIN"),
    CLIENT(1, "ROLE_CLIENT");

    private int number;
    private String description;

    public static Profile toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Profile profile : Profile.values()){
            if(cod.equals(profile.getNumber())){
                return profile;
            }
        }
        throw new IllegalArgumentException("ID INVALID: " + cod);
    }
}
