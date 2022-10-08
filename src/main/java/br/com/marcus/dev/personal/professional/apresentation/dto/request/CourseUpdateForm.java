package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
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
    private List<ListLanguageProgrammer> listLanguageProgrammers = new ArrayList<>();
    private List<ListFramework> listFramework = new ArrayList<>();
}
