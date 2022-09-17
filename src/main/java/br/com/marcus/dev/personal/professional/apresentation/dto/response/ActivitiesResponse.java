package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class ActivitiesResponse {

    private LocalDate date;
    private String description;
    private CourseResponse courseResponse;
    private CertificateResponse certificateResponse;
    private ProfessionalExperienceResponse professionalExperienceResponse;
    private GraduationResponse graduationResponse;
    private ProfessionalGoalResponse professionalGoalResponse;
    private SoftSkillsResponse softSkillsResponse;
    private HardSkillsResponse hardSkillsResponse;
}
