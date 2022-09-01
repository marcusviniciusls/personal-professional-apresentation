package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import br.com.marcus.dev.personal.professional.apresentation.entities.Partner;
import br.com.marcus.dev.personal.professional.apresentation.entities.Subject;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class GraduationResponse {

    private String name;
    private BigDecimal qtdHours;
    private LocalDate dateInitPreview;
    private LocalDate dateFinishPreview;
    private LocalDate dateInitReal;
    private LocalDate dateFinishReal;
    private String location;
    private BigDecimal noteFinish;
    private String urlUniversityDegree;
    private SituationGraduation situationGraduation;
    private TypeGraduation typeGraduation;
    private PartnerResponse partnerResponse;
    private List<SubjectResponse> listSubject = new ArrayList<>();
}
