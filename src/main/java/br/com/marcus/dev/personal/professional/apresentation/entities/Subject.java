package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.SituationSubject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_subject")
public class Subject extends SuperEntity{

    private String name;
    private int qtdHours;
    private BigDecimal note;
    private String description;
    private String period;
    @Enumerated(EnumType.ORDINAL)
    private SituationSubject situationSubject;
    @ManyToOne
    @JoinColumn(name = "graduation_id")
    private Graduation graduation;
}
