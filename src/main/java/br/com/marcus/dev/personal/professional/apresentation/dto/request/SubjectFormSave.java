package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectFormSave {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Quantity Hours cannot be blank")
    private BigDecimal qtdHours;
    @NotNull(message = "Note cannot be blank")
    private BigDecimal note;
    private String description;
    @NotBlank(message = "Period cannot be blank")
    private String period;
    @NotNull(message = "Situation Subject cannot be blank")
    private Integer situationSubject;
    private String imageReportRecord;
}
