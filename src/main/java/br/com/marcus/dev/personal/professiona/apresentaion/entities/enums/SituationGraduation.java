package br.com.marcus.dev.personal.professiona.apresentaion.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituationGraduation {

    APPROVED(0),
    DISAPPROVED(1);

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
