package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PartSaveForm {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Level cannot be blank")
    private Integer level;
    @NotNull(message = "Language Id cannot be blank")
    private UUID languageId;
    private List<ListMaterial> listMaterial = new ArrayList<>();
}
