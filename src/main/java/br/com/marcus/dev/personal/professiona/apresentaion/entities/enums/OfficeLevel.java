package br.com.marcus.dev.personal.professiona.apresentaion.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OfficeLevel {

    INTERNSHIP(0),
    JUNIOR(1),
    FULL(2),
    SENIOR(3),
    TECH_LEADER(4);

    private int number;

    public static OfficeLevel toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(OfficeLevel officeLevel : OfficeLevel.values()){
            if(cod.equals(officeLevel.getNumber())){
                return officeLevel;
            }
        }
        throw new IllegalArgumentException("ID INVALID: " + cod);
    }
}
