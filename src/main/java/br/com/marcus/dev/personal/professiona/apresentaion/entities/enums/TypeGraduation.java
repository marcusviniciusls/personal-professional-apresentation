package br.com.marcus.dev.personal.professiona.apresentaion.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeGraduation {

    PRIMARY_SCHOOL(0),
    ELEMENTARY_SCHOOL(1),
    HIGH_SCHOOL(2),
    TECHNOLOGIST(3),
    GRADUATION(4),
    GRADUATE(5),
    MASTERS_DEGREE(6),
    DOCTORATE(7),
    POST_DOCTORAL(8);

    private int number;

    public static TypeGraduation toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(TypeGraduation typeGraduation : TypeGraduation.values()){
            if(cod.equals(typeGraduation.getNumber())){
                return typeGraduation;
            }
        }
        throw new IllegalArgumentException("ID INVALID: " + cod);
    }
}
