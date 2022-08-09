package br.com.marcus.dev.personal.professional.apresentation.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituationGraduation {

    CONCLUSION(0),
    NOT_CONCLUSION(1),
    IN_PROGRESS(2);

    private int number;

    public static SituationGraduation toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(SituationGraduation situationGraduation : SituationGraduation.values()){
            if(cod.equals(situationGraduation.getNumber())){
                return situationGraduation;
            }
        }
        throw new IllegalArgumentException("ID INVALID: " + cod);
    }
}
