package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CourseSaveForm {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
    @NotNull(message = "Duration cannot be blank")
    private BigDecimal duration;
    @NotNull(message = "Date Init Expected cannot be blank")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInitExpected;
    @NotNull(message = "Date Finish Expected cannot be blank")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinishExpected;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInitReal;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinishReal;
    private String logoImage;
    @NotNull(message = "Status Course cannot be blank")
    private Integer statusCourse;
    @NotNull(message = "Level Course cannot be blank")
    private Integer levelCourse;
    private List<ListFramework> listFrameworkId;
    private List<ListLanguageProgrammer> listLanguageProgrammerId;

    public CourseSaveForm(String name, String description, BigDecimal duration, LocalDate dateInitExpected, LocalDate dateFinishExpected, LocalDate dateInitReal, LocalDate dateFinishReal, String logoImage, Integer statusCourse, Integer levelCourse) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.dateInitExpected = dateInitExpected;
        this.dateFinishExpected = dateFinishExpected;
        this.dateInitReal = dateInitReal;
        this.dateFinishReal = dateFinishReal;
        this.logoImage = logoImage;
        this.statusCourse = statusCourse;
        this.levelCourse = levelCourse;
    }
}
