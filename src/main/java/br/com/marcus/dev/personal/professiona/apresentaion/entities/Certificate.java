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
@Table(name = "tb_certificate")
public class Certificate extends SuperEntity {

    private String name;
    private String logoImage;
    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;
}
