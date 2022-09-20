package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class PartUpdateForm {

    private String name;
    private Integer level;
    private UUID languageId;
    private List<ListMaterial> listMaterial = new ArrayList<>();
}
