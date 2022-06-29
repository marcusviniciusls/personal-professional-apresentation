package br.com.marcus.dev.personal.professiona.apresentaion.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MaritalStatus {

    SINGLE(0),
    FALLINGINLOVE(1),
    MARRIED(2),
    DIVORCED(3),
    WIDOWER(4),
    OTHER(5);

    private int number;

    public static MaritalStatus toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(MaritalStatus maritalStatus : MaritalStatus.values()){
            if(cod.equals(maritalStatus.getNumber())){
                return maritalStatus;
            }
        }
        throw new IllegalArgumentException("ID INVALID: " + cod);
    }
}
