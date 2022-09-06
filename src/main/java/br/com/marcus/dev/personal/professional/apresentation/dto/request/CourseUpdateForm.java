package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class CourseUpdateForm {

    private String name;
    private String description;
    private BigDecimal duration;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInitExpected;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinishExpected;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInitReal;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinishReal;
    private Integer statusCourse;
    private Integer levelCourse;
}
