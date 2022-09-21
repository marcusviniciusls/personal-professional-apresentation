package br.com.marcus.dev.personal.professional.apresentation.dto.request;

import br.com.marcus.dev.personal.professional.apresentation.entities.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class StudyPlanSaveForm {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull(message = "Level cannot be blank")
    private Integer level;
    private UUID languageProgrammerId;
    private UUID frameworkId;
    private List<ListTopic> listTopic = new ArrayList<>();
    private List<ListCourse> listCourse = new ArrayList<>();
    private List<ListProfessionalExperience> listProfessionalExperience = new ArrayList<>();
    private List<ListAssignments> listAssignments = new ArrayList<>();
    private List<ListGraduation> listGraduation = new ArrayList<>();
    private List<ListCertificate> listCertificate = new ArrayList<>();
}
