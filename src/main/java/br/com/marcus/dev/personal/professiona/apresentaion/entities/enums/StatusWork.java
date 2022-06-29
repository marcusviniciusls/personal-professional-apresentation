package br.com.marcus.dev.personal.professiona.apresentaion.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusWork {

    CURRENT(0),
    OLD(1);

    private int number;

    public static StatusWork toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(StatusWork statusWork : StatusWork.values()){
            if(cod.equals(statusWork.getNumber())){
                return statusWork;
            }
        }
        throw new IllegalArgumentException("ID INVALID: " + cod);
    }
}
