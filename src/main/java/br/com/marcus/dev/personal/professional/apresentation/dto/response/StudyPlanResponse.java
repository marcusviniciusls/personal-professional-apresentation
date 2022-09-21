package br.com.marcus.dev.personal.professional.apresentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class StudyPlanResponse {

    private String name;
    private Integer level;
    private LanguageProgrammerResponse languageProgrammerResponse;
    private FrameworkResponse frameworkResponse;
    private List<TopicResponse> listTopicResponse = new ArrayList<>();
    private List<CourseResponse> listCourseResponse = new ArrayList<>();
    private List<ProfessionalExperienceResponse> listProfessionalExperienceResponse = new ArrayList<>();
    private List<AssignmentsResponse> listAssignmentsResponse = new ArrayList<>();
    private List<GraduationResponse> listGraduationResponse = new ArrayList<>();
    private List<CertificateResponse> listCertificateResponse = new ArrayList<>();

    public void addTopicResponse(TopicResponse topicResponse){
        this.listTopicResponse.add(topicResponse);
    }

    public void addCourseResponse(CourseResponse courseResponse){
        this.listCourseResponse.add(courseResponse);
    }

    public void addProfessionalExperienceResponse(ProfessionalExperienceResponse professionalExperienceResponse){
        this.listProfessionalExperienceResponse.add(professionalExperienceResponse);
    }

    public void addAssignmentsResponse(AssignmentsResponse assignmentsResponse){
        this.listAssignmentsResponse.add(assignmentsResponse);
    }

    public void addGraduationResponse(GraduationResponse graduationResponse){
        this.listGraduationResponse.add(graduationResponse);
    }

    public void addCertificateResponse(CertificateResponse certificateResponse){
        this.listCertificateResponse.add(certificateResponse);
    }
}
