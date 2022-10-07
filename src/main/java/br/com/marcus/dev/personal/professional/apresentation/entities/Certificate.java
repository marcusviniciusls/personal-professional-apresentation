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
@Table(name = "tb_certificate")
public class Certificate extends SuperEntity {

    private String name;
    private String logoImage;
    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    public Certificate(UUID id) {
        super(id);
    }

    public Certificate(UUID id, String name, String logoImage) {
        super(id);
        this.name = name;
        this.logoImage = logoImage;
    }
}
