package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import br.com.marcus.dev.personal.professional.apresentation.entities.BranchActivity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class PartnerRequestFullFormSave {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "UrlImage cannot be blank")
    private String urlImage;
    @NotBlank(message = "Branch Activity cannot be blank")
    private String branchActivity;
    private String description;
}
