package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class GraduationFormSave {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInitPreview;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinishPreview;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInitReal;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinishReal;
    @NotBlank(message = "Location cannot be blank")
    private String location;
    @NotNull
    private Integer typeGraduation;
    @NotBlank(message = "Partner Graduation cannot be blank")
    private String partnerId;
    private String urlUniversityDegree;
    @NotEmpty
    private List<@Valid SubjectFormSave> listSubjectFormSave = new ArrayList<>();
}