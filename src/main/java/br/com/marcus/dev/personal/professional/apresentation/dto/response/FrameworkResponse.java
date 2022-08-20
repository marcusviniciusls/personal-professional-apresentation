package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FrameworkResponse {

    private String name;
    private String description;
    private CourseResponse courseResponse;
}
