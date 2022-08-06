package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_professional_experience_resume")
public class ProfessionalExperienceResume extends SuperEntity{

    private LocalDate dateInit;
    private LocalDate dateFinish;
    @OneToMany(mappedBy = "professionalExperienceResume")
    private List<ProfessionalExperience> listProfessionalExperience = new ArrayList<>();

    public ProfessionalExperienceResume(LocalDate dateInit, LocalDate dateFinish) {
        this.dateInit = dateInit;
        this.dateFinish = dateFinish;
    }

    public void addListProfessionalExperience(ProfessionalExperience professionalExperience){
        this.listProfessionalExperience.add(professionalExperience);
    }
}
