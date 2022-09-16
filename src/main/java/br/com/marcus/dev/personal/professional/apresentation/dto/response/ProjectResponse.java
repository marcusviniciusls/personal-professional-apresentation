package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import br.com.marcus.dev.personal.professional.apresentation.entities.Framework;
import br.com.marcus.dev.personal.professional.apresentation.entities.LanguageProgrammer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProjectResponse {

    private String title;
    private String description;
    private String linkGitHub;
    private String linkYoutube;
    private List<LanguageProgrammerResponse> listLanguageProgrammerResponse = new ArrayList<>();
    private List<FrameworkResponse> listFrameworkResponse = new ArrayList<>();

    public void addListLanguageProgrammerResponse(LanguageProgrammerResponse languageProgrammer){
        this.listLanguageProgrammerResponse.add(languageProgrammer);
    }

    public void addListFrameworkResponse(FrameworkResponse framework){
        this.listFrameworkResponse.add(framework);
    }
}