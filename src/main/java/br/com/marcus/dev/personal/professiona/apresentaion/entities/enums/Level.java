package br.com.marcus.dev.personal.professiona.apresentaion.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum Level {

    BEGINNER(0),
    BASIC(1),
    INTERMEDIARY(2),
    ADVANCED(3),
    EXPERT(4);

    private int number;

    public static Level toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(Level level : Level.values()){
            if(cod.equals(level.getNumber())){
                return level;
            }
        }
        throw new IllegalArgumentException("ID INVALID: " + cod);
    }
}
