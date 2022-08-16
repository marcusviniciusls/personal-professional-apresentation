package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Setter
@Getter
@NoArgsConstructor
public class EmailForm {

    @Email
    private String email;
}
