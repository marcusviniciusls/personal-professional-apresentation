package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProjectSaveForm {

    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String description;
    @NotBlank(message = "Github cannot be blank")
    private String linkGitHub;
    private String linkYoutube;
    private List<ListLanguageProgrammer> listLanguageProgrammers = new ArrayList<>();
    private List<ListFramework> listFramework = new ArrayList<>();
}
