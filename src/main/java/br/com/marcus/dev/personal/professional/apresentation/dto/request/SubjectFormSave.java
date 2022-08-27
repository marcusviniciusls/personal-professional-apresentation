package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class SubjectFormSave {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Quantity Hours cannot be blank")
    private BigDecimal qtdHours;
    @NotBlank(message = "Note cannot be blank")
    private BigDecimal note;
    private String description;
    @NotBlank(message = "Period cannot be blank")
    private String period;
    @NotBlank(message = "Situation Subject cannot be blank")
    private Integer situationSubject;
    private String imageReportRecord;
}
