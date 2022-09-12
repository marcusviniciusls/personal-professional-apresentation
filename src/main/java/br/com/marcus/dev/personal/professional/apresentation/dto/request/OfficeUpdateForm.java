package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OfficeUpdateForm {

    private String name;
    private String description;
    private Integer officeLevel;
}
