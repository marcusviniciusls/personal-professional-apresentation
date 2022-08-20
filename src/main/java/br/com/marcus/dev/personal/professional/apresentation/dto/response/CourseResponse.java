package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class CourseResponse {

    private String name;
    private String description;
    private BigDecimal duration;
    private LocalDate dateInitExpected;
    private LocalDate dateFinishExpected;
    private LocalDate dateInitReal;
    private LocalDate dateFinishReal;
    private String logoImage;
    private Integer statusCourse;
    private Integer levelCourse;
}
