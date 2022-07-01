package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.Level;
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

    public void addListTopic(Topic topic){
        this.listTopic.add(topic);
    }
}