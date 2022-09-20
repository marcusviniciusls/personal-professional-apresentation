package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class LanguageSaveForm {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Level cannot be blank")
    private Integer level;
    private List<ListPart> listPart = new ArrayList<>();
}
