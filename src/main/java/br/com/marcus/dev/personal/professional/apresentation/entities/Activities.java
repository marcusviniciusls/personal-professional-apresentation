package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_activities")
public class Activities extends SuperEntity{

    private LocalDate date;
    private String description;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "certificate_id")
    private Certificate certificate;
    @ManyToOne
    @JoinColumn(name = "professional_experience_id")
    private ProfessionalExperience professionalExperience;
    @ManyToOne
    @JoinColumn(name = "graduation_id")
    private Graduation graduation;
    @ManyToOne
    @JoinColumn(name = "professional_goal_id")
    private ProfessionalGoal professionalGoal;
    @ManyToOne
    @JoinColumn(name = "soft_skills_id")
    private SoftSkills softSkills;
    @ManyToOne
    @JoinColumn(name = "hard_skills_id")
    private HardSkills hardSkills;

    public Activities(LocalDate date, String description, Course course) {
        this.date = date;
        this.description = description;
        this.course = course;
    }

    public Activities(UUID id) {
        super(id);
    }
}
