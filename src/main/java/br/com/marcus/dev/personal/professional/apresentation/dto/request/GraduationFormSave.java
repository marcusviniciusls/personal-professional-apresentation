package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
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
    private BigDecimal qtdHours;
    @NotBlank(message = "Data Init Preview cannot be blank")
    private LocalDate dateInitPreview;
    @NotBlank(message = "Data Finish Preview cannot be blank")
    private LocalDate dateFinishPreview;
    private LocalDate dateInitReal;
    private LocalDate dateFinishReal;
    @NotBlank(message = "Location cannot be blank")
    private String location;
    private BigDecimal noteFinish;
    @NotBlank(message = "Situation Graduation cannot be blank")
    private Integer situationGraduation;
    @NotBlank(message = "Type Graduation cannot be blank")
    private Integer typeGraduation;
    @NotBlank(message = "Partner Graduation cannot be blank")
    private UUID partnerId;
    private String urlUniversityDegree;
    private List<@NotBlank(message = "Partner Graduation cannot be blank") SubjectFormSave> listSubjectFormSave = new ArrayList<>();
}
