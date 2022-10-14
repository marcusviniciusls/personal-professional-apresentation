package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubjectFormUpdate {

    private String name;
    private BigDecimal qtdHours;
    private BigDecimal note;
    private String description;
    private String period;
    private Integer situationSubject;
    private UUID graduationId;

    public SubjectFormUpdate(String name, String description, String period, Integer situationSubject, UUID graduationId) {
        this.name = name;
        this.description = description;
        this.period = period;
        this.situationSubject = situationSubject;
        this.graduationId = graduationId;
    }

    public SubjectFormUpdate(String name, BigDecimal qtdHours, BigDecimal note, String description, String period, Integer situationSubject) {
        this.name = name;
        this.qtdHours = qtdHours;
        this.note = note;
        this.description = description;
        this.period = period;
        this.situationSubject = situationSubject;
    }
}
