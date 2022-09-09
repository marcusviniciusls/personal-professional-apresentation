package br.com.marcus.dev.personal.professional.apresentation.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class HardSkillsSaveForm {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    private String description;
    private Integer level;
    private List<ListFramework> listIdFramework = new ArrayList<>();
    private List<ListLanguageProgrammer> listIdLanguageProgrammer = new ArrayList<>();
}
