package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GraduationFormUpdate {

    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInitPreview;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinishPreview;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInitReal;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinishReal;
    private String location;
    private Integer typeGraduation;
    private UUID partnerId;
}
