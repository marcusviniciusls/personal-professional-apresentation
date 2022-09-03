package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import br.com.marcus.dev.personal.professional.apresentation.entities.Graduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
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
}
