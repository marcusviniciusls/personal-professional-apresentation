package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicSaveForm {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Study Plan Id cannot be blank")
    private UUID studyPlanId;

    public TopicSaveForm(String name) {
        this.name = name;
    }
}
