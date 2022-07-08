package br.com.marcus.dev.personal.professiona.apresentaion.dto;

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
