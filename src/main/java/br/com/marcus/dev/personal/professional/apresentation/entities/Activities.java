package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    public Activities(LocalDate date, String description, Course course) {
        this.date = date;
        this.description = description;
        this.course = course;
    }
}
