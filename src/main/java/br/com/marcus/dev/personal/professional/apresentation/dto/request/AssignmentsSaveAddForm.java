package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class AssignmentsSaveAddForm {

    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotNull(message = "ID Professional Experience cannot be blank")
    private UUID idProfessionalExperience;
}
