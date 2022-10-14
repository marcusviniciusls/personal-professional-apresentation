package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PartnerRequestFormUpdate {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
    @NotNull(message = "Branch Activity ID cannot be blank")
    private UUID branchActivityId;
}
