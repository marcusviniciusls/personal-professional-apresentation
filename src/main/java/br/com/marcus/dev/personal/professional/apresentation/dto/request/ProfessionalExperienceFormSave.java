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
@Getter
@Setter
public class ProfessionalExperienceFormSave {

    @NotNull(message = "Date Init cannot be blank")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInit;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinish;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @NotNull(message = "Office cannot be blank")
    private Integer officeEnum;
    @NotNull(message = "Partner cannot be blank")
    private UUID partnerId;
    @NotNull(message = "Office cannot be blank")
    private UUID officeId;
    @NotEmpty
    private List<@Valid AssignmentsSaveForm> listAssignmentsSaveForm = new ArrayList<>();

    public void addListAssignmentsSaveForm(AssignmentsSaveForm assignmentsSaveForm){
        this.listAssignmentsSaveForm.add(assignmentsSaveForm);
    }
}
