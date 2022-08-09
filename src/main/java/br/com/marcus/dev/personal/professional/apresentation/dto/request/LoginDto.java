package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginDto {

    private String email;
    private String password;
}
