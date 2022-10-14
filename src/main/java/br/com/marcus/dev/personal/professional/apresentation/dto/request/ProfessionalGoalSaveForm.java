package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfessionalGoalSaveForm {

    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotBlank(message = "Office cannot be blank")
    private String office;
}
