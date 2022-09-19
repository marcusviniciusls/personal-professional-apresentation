package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class HardSkillsUpdateForm {

    private String name;
    private String description;
    private Integer level;
    private List<ListFramework> listIdFramework = new ArrayList<>();
    private List<ListLanguageProgrammer> listIdLanguageProgrammer = new ArrayList<>();
}
