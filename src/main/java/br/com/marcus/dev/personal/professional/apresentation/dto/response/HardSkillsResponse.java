package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class HardSkillsResponse {

    private UUID id;
    private String name;
    private String description;
    private Level level;
    private LanguageProgrammerResponse languageProgrammerResponse;
    private FrameworkResponse frameworkResponse;
}
