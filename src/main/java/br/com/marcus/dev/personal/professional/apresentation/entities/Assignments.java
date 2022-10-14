package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_assignments")
public class Assignments extends SuperEntity{

    private String description;
    @ManyToOne
    @JoinColumn(name = "professionalExperience_id")
    private ProfessionalExperience professionalExperience;

    public Assignments(UUID id) {
        super(id);
    }

    public Assignments(UUID id, String description, ProfessionalExperience professionalExperience) {
        super(id);
        this.description = description;
        this.professionalExperience = professionalExperience;
    }

    public Assignments(UUID id, String description) {
        super(id);
        this.description = description;
    }

    public Assignments(String description) {
        this.description = description;
    }

    public Assignments(String description, ProfessionalExperience professionalExperience) {
        this.description = description;
        this.professionalExperience = professionalExperience;
    }
}
