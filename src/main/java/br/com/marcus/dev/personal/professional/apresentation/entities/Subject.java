package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_subject")
public class Subject extends SuperEntity{

    private String name;
    private BigDecimal qtdHours;
    private BigDecimal note;
    private String description;
    private String period;
    private String imageReportRecord;
    @Enumerated(EnumType.ORDINAL)
    private SituationSubject situationSubject;
    @ManyToOne
    @JoinColumn(name = "graduation_id")
    private Graduation graduation;

    public Subject(String name, BigDecimal qtdHours, BigDecimal note, String description, String period, String imageReportRecord, SituationSubject situationSubject, Graduation graduation) {
        this.name = name;
        this.qtdHours = qtdHours;
        this.note = note;
        this.description = description;
        this.period = period;
        this.imageReportRecord = imageReportRecord;
        this.situationSubject = situationSubject;
        this.graduation = graduation;
    }
}
