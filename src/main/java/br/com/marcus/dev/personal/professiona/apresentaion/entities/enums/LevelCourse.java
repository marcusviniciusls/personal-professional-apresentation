package br.com.marcus.dev.personal.professiona.apresentaion.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LevelCourse {

    BASIC(0),
    INTERMEDIATE(1),
    ADVANCED(2),
    EXPERT(3);

    private int number;

    public static LevelCourse toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(LevelCourse levelCourse : LevelCourse.values()){
            if(cod.equals(levelCourse.getNumber())){
                return levelCourse;
            }
        }
        throw new IllegalArgumentException("ID INVALID: " + cod);
    }
}
