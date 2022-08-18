package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class TelephoneFormSave {

    @NotBlank(message = "DDI cannot be blank")
    private String ddi;
    @NotBlank(message = "DDD cannot be blank")
    private String ddd;
    @NotBlank(message = "Number cannot be blank")
    private String number;
    @NotBlank
    private String id;
}
