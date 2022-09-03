package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@NoArgsConstructor
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
}
