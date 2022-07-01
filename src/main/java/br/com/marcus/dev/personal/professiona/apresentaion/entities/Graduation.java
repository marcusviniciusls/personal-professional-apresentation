package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.SituationGraduation;
import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.TypeGraduation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
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

    private void addListSubject(Subject subject){
        this.listSubject.add(subject);
    }
}
