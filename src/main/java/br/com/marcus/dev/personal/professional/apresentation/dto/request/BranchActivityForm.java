package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BranchActivityForm {

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be blank")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
}
