package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OfficeSaveForm {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
    @NotNull
    private Integer officeLevel;
}
