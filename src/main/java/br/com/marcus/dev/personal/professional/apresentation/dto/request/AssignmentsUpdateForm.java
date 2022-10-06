package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AssignmentsUpdateForm {

    private String description;
    private UUID idProfessionalExperience;

    public AssignmentsUpdateForm(String description) {
        this.description = description;
    }
}
