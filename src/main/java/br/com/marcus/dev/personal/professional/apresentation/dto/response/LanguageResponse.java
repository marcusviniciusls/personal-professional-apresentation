package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class LanguageResponse {

    private String name;
    private Level level;
    private List<PartResponse> listPart = new ArrayList<>();

    public void addListPart(PartResponse partResponse){
        this.listPart.add(partResponse);
    }
}
