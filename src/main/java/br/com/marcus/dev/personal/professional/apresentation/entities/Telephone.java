package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_telephone")
public class Telephone extends SuperEntity{

    private String ddi;
    private String ddd;
    private String number;
    @ManyToOne
    @JoinColumn(name = "data_personal_id")
    private DataPersonal dataPersonal;

    public Telephone(UUID id) {
        super(id);
    }

    public Telephone(String ddi, String ddd, String number) {
        this.ddi = ddi;
        this.ddd = ddd;
        this.number = number;
    }
}
