package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class SoftSkillsFormSave {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    public SoftSkillsFormSave(String name){
        this.name = name;
    }
}
