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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class GraduationFormSave {

    private String name;
    private int qtdHours;
    private LocalDate dateInitPreview;
    private LocalDate dateFinishPreview;
    private LocalDate dateInitReal;
    private LocalDate dateFinishReal;
    private String location;
    private BigDecimal noteFinish;
    private Integer situationGraduation;
    private Integer typeGraduation;
    private UUID partnerId;
    private List<SubjectFormSave> listSubjectFormSave = new ArrayList<>();
}
