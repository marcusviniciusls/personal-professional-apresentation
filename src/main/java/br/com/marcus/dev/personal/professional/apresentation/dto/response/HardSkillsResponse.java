package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class HardSkillsResponse {

    private UUID id;
    private String name;
    private String description;
    private Level level;
    private List<LanguageProgrammerResponse> listLanguageProgrammerResponse = new ArrayList<>();
    private List<FrameworkResponse> listFrameworkResponse = new ArrayList<>();

    public void addListLanguageProgrammerResponse(LanguageProgrammerResponse languageProgrammerResponse){
        this.listLanguageProgrammerResponse.add(languageProgrammerResponse);
    }

    public void addListFrameworkResponse(FrameworkResponse frameworkResponse){
        this.listFrameworkResponse.add(frameworkResponse);
    }
}
