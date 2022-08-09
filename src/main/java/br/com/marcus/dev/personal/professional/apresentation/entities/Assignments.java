package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_assignments")
public class Assignments extends SuperEntity{

    private String description;
    @ManyToOne
    @JoinColumn(name = "professionalExperience_id")
    private ProfessionalExperience professionalExperience;
}
