package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
@Component
public class SubjectResponse {

    private String name;
    private BigDecimal qtdHours;
    private BigDecimal note;
    private String description;
    private String period;
    private String imageReportRecord;
    private SituationSubject situationSubject;
}
