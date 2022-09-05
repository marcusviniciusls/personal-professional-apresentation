package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusCourse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
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
}
