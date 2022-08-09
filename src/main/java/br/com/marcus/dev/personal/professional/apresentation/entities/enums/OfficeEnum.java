package br.com.marcus.dev.personal.professional.apresentation.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OfficeEnum {

    INTERNSHIP(0),
    CLT(1),
    PJ(2),
    YOUNG_APPRENTICE(3),
    FREELANCER(4);

    private int number;

    public static OfficeEnum toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(OfficeEnum officeEnum : OfficeEnum.values()){
            if(cod.equals(officeEnum.getNumber())){
                return officeEnum;
            }
        }
        throw new IllegalArgumentException("ID INVALID: " + cod);
    }
}
