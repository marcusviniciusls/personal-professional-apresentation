package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.dto.response.*;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_study_plan")
public class StudyPlan extends SuperEntity{

    private String name;
    @Enumerated(EnumType.ORDINAL)
    private Level level;
    @ManyToOne
    @JoinColumn(name = "language_programmer_id")
    private LanguageProgrammer languageProgrammer;
    @ManyToOne
    @JoinColumn(name = "framework_id")
    private Framework framework;
    @OneToMany(mappedBy = "studyPlan")
    private List<Topic> listTopic = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tb_studyplan_course", joinColumns =
            {@JoinColumn(name = "study_plan_id")}, inverseJoinColumns =
            {@JoinColumn(name="course_id")})
    private List<Course> listCourse = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tb_studyplan_professionalExperience", joinColumns =
            {@JoinColumn(name = "study_plan_id")}, inverseJoinColumns =
            {@JoinColumn(name="professional_experience_id")})
    private List<ProfessionalExperience> listProfessionalExperience = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tb_studyplan_assignments", joinColumns =
            {@JoinColumn(name = "study_plan_id")}, inverseJoinColumns =
            {@JoinColumn(name="assignments_id")})
    private List<Assignments> listAssignments = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tb_studyplan_graduation", joinColumns =
            {@JoinColumn(name = "study_plan_id")}, inverseJoinColumns =
            {@JoinColumn(name="graduation_id")})
    private List<Graduation> listGraduation = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "tb_studyplan_certificate", joinColumns =
            {@JoinColumn(name = "study_plan_id")}, inverseJoinColumns =
            {@JoinColumn(name="certificate_id")})
    private List<Certificate> listCertificate = new ArrayList<>();

    public StudyPlan(String name, Level level, LanguageProgrammer languageProgrammer, Framework framework) {
        this.name = name;
        this.level = level;
        this.languageProgrammer = languageProgrammer;
        this.framework = framework;
    }

    public void addTopic(Topic topic){
        this.listTopic.add(topic);
    }

    public void addCourse(Course course){
        this.listCourse.add(course);
    }

    public void addProfessionalExperience(ProfessionalExperience professionalExperience){
        this.listProfessionalExperience.add(professionalExperience);
    }

    public void addAssignments(Assignments assignments){
        this.listAssignments.add(assignments);
    }

    public void addGraduation(Graduation graduation){
        this.listGraduation.add(graduation);
    }

    public void addCertificate(Certificate certificate){
        this.listCertificate.add(certificate);
    }
}