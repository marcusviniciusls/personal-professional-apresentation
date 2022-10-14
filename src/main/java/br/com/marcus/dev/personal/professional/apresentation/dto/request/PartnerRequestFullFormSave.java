package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartnerRequestFullFormSave {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String branchActivity;
    private String description;
}
