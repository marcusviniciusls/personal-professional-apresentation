package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import br.com.marcus.dev.personal.professional.apresentation.entities.Material;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class PartSaveForm {

    private String name;
    private Integer level;
    private UUID languageId;
    private List<ListMaterial> listMaterial = new ArrayList<>();
}
