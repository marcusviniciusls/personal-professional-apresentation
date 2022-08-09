package br.com.marcus.dev.personal.professional.apresentation.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
