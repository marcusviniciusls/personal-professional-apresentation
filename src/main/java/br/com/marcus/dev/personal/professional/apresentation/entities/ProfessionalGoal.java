package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_professional_goal")
public class ProfessionalGoal extends SuperEntity{

    private String description;
    private String office;

    public ProfessionalGoal(UUID id) {
        super(id);
    }
}
