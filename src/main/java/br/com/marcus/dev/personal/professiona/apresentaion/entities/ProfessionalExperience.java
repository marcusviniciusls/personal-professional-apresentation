package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.OfficeEnum;
import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.StatusWork;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_professional_experience")
public class ProfessionalExperience extends SuperEntity{

    private LocalDate dateInit;
    private LocalDate dateFinish;
    @Enumerated(EnumType.ORDINAL)
    private OfficeEnum officeEnum;
    @Enumerated(EnumType.ORDINAL)
    private StatusWork statusWork;
    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;
    @ManyToOne
    @JoinColumn(name = "office_id")
    private Office office;
    @ManyToOne
    @JoinColumn(name = "professionalExperienceResume_id")
    private ProfessionalExperienceResume professionalExperienceResume;
    @OneToMany(mappedBy = "professionalExperience")
    private List<Assignments> listAssignments = new ArrayList<>();
}
