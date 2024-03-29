package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.TypeGraduation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_graduation")
public class Graduation extends SuperEntity {

    private String name;
    private BigDecimal qtdHours;
    private LocalDate dateInitPreview;
    private LocalDate dateFinishPreview;
    private LocalDate dateInitReal;
    private LocalDate dateFinishReal;
    private String location;
    private BigDecimal noteFinish;
    private String urlUniversityDegree;
    @Enumerated(EnumType.ORDINAL)
    private SituationGraduation situationGraduation;
    @Enumerated(EnumType.ORDINAL)
    private TypeGraduation typeGraduation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "partner_id")
    private Partner partner;
    @OneToMany(mappedBy = "graduation", fetch = FetchType.LAZY)
    private List<Subject> listSubject = new ArrayList<>();

    public Graduation(String name, BigDecimal qtdHours, LocalDate dateInit, LocalDate dateFinish, String location, BigDecimal noteFinish, SituationGraduation situationGraduation, TypeGraduation typeGraduation, Partner partner) {
        this.name = name;
        this.qtdHours = qtdHours;
        this.dateInitReal = dateInit;
        this.dateFinishReal = dateFinish;
        this.location = location;
        this.noteFinish = noteFinish;
        this.situationGraduation = situationGraduation;
        this.typeGraduation = typeGraduation;
        this.partner = partner;
    }

    public Graduation(UUID id) {
        super(id);
    }

    public void addListSubject(Subject subject){
        this.listSubject.add(subject);
    }
}
