package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_graduation")
public class Graduation extends SuperEntity {

    private String name;
    private int qtdHours;
    private LocalDate dateInit;
    private LocalDate dateFinish;
    private String location;
    private BigDecimal noteFinish;
    @Enumerated(EnumType.ORDINAL)
    private SituationGraduation situationGraduation;
    @Enumerated(EnumType.ORDINAL)
    private TypeGraduation typeGraduation;
    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;
    @OneToMany(mappedBy = "graduation")
    private List<Subject> listSubject = new ArrayList<>();

    public Graduation(String name, int qtdHours, LocalDate dateInit, LocalDate dateFinish, String location, BigDecimal noteFinish, SituationGraduation situationGraduation, TypeGraduation typeGraduation, Partner partner) {
        this.name = name;
        this.qtdHours = qtdHours;
        this.dateInit = dateInit;
        this.dateFinish = dateFinish;
        this.location = location;
        this.noteFinish = noteFinish;
        this.situationGraduation = situationGraduation;
        this.typeGraduation = typeGraduation;
        this.partner = partner;
    }

    private void addListSubject(Subject subject){
        this.listSubject.add(subject);
    }
}
