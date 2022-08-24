package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SubjectFormSave {

    private String name;
    private int qtdHours;
    private BigDecimal note;
    private String description;
    private String period;
    private Integer situationSubject;
}
