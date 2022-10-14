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
public class SubjectFormOnlySave {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Quantity Hours cannot be null")
    private BigDecimal qtdHours;
    @NotNull(message = "Note cannot be null")
    private BigDecimal note;
    private String description;
    private String period;
    @NotNull(message = "Situation Subject cannot be null")
    private Integer situationSubject;
    @NotNull(message = "Graduation Subject cannot be null")
    private UUID graduationId;

    public SubjectFormOnlySave(String name, BigDecimal qtdHours, BigDecimal note, String description, String period, Integer situationSubject) {
        this.name = name;
        this.qtdHours = qtdHours;
        this.note = note;
        this.description = description;
        this.period = period;
        this.situationSubject = situationSubject;
    }
}
