package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class MaterialSaveForm {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Part ID cannot be blank")
    private UUID partId;
}
