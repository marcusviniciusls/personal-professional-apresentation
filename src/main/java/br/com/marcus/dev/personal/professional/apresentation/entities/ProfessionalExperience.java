package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeEnum;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusWork;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    public ProfessionalExperience(LocalDate dateInit, LocalDate dateFinish, OfficeEnum officeEnum, StatusWork statusWork, Partner partner, Office office, ProfessionalExperienceResume professionalExperienceResume) {
        this.dateInit = dateInit;
        this.dateFinish = dateFinish;
        this.officeEnum = officeEnum;
        this.statusWork = statusWork;
        this.partner = partner;
        this.office = office;
        this.professionalExperienceResume = professionalExperienceResume;
    }
}