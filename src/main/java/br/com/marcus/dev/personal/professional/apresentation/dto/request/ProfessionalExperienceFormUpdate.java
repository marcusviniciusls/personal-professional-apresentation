package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class ProfessionalExperienceFormUpdate {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInit;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinish;
    private String description;
    private Integer officeEnum;
    private UUID partnerId;
    private UUID officeId;
    private List<AssignmentsProfessionalUpdateForm> listAssignments = new ArrayList<>();
}
