package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class CertificateSaveForm {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Partner ID cannot be blank")
    private UUID partnerId;
}
