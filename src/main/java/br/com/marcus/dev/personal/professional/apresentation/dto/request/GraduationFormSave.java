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

@NoArgsConstructor
@Setter
@Getter
public class GraduationFormSave {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInitPreview;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinishPreview;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateInitReal;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFinishReal;
    @NotBlank(message = "Location cannot be blank")
    private String location;
    @NotNull
    private Integer typeGraduation;
    @NotBlank(message = "Partner Graduation cannot be blank")
    private String partnerId;
    private String urlUniversityDegree;
    @NotEmpty
    private List<@Valid SubjectFormSave> listSubjectFormSave = new ArrayList<>();

    public GraduationFormSave(String name, LocalDate dateInitPreview, LocalDate dateFinishPreview, LocalDate dateInitReal, LocalDate dateFinishReal, String location, Integer typeGraduation, String partnerId, String urlUniversityDegree) {
        this.name = name;
        this.dateInitPreview = dateInitPreview;
        this.dateFinishPreview = dateFinishPreview;
        this.dateInitReal = dateInitReal;
        this.dateFinishReal = dateFinishReal;
        this.location = location;
        this.typeGraduation = typeGraduation;
        this.partnerId = partnerId;
        this.urlUniversityDegree = urlUniversityDegree;
    }

    public GraduationFormSave(String name, LocalDate dateInitPreview, LocalDate dateFinishPreview, LocalDate dateInitReal, String location, Integer typeGraduation, String partnerId, String urlUniversityDegree) {
        this.name = name;
        this.dateInitPreview = dateInitPreview;
        this.dateFinishPreview = dateFinishPreview;
        this.dateInitReal = dateInitReal;
        this.location = location;
        this.typeGraduation = typeGraduation;
        this.partnerId = partnerId;
        this.urlUniversityDegree = urlUniversityDegree;
    }

    public void addListSubjectFormSave(SubjectFormSave subjectFormSave){
        this.listSubjectFormSave.add(subjectFormSave);
    }
}
