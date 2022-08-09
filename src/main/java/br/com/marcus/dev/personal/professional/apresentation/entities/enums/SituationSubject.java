package br.com.marcus.dev.personal.professional.apresentation.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituationSubject {

    APPROVED(0),
    NOT_APPROVED(1);

    private int number;

    public static SituationSubject toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(SituationSubject situationSubject : SituationSubject.values()){
            if(cod.equals(situationSubject.getNumber())){
                return situationSubject;
            }
        }
        throw new IllegalArgumentException("ID INVALID: " + cod);
    }
}
