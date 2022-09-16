package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProjectUpdateForm {

    private String title;
    private String description;
    private String linkGitHub;
    private String linkYoutube;
    private List<ListLanguageProgrammer> listLanguageProgrammers = new ArrayList<>();
    private List<ListFramework> listFramework = new ArrayList<>();
}
