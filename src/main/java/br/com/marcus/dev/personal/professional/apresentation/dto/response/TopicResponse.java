package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TopicResponse {

    private String name;
    private UUID studyPlanId;
}
