package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_telephone")
public class Telephone extends SuperEntity{

    private String ddi;
    private String ddd;
    private String number;
    @ManyToOne
    @JoinColumn(name = "data_personal_id")
    private DataPersonal dataPersonal;
}
