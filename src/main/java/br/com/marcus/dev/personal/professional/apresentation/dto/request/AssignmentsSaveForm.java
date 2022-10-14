package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AssignmentsSaveForm {

    @NotNull(message = "Description cannot be blank")
    private String description;
}
