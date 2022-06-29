package br.com.marcus.dev.personal.professiona.apresentaion.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum StatusCourse {

    NOTSTARTED(0),
    PROGRESS(1),
    CONCLUSION(2);

    private int number;

    public static StatusCourse toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        for(StatusCourse statusCourse : StatusCourse.values()){
            if(cod.equals(statusCourse.getNumber())){
                return statusCourse;
            }
        }
        throw new IllegalArgumentException("ID INVALID: " + cod);
    }
}
