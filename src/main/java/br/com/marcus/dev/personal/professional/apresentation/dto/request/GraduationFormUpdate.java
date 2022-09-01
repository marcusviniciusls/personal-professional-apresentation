package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
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
