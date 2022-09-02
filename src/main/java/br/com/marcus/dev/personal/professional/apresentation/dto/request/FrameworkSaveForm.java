package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class FrameworkSaveForm {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
}
