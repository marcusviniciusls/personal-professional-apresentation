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
@Table(name = "tb_email")
public class Email extends SuperEntity {

    private String email;
    @ManyToOne
    @JoinColumn(name = "data_personal_id")
    private DataPersonal dataPersonal;

    public Email(UUID id) {
        super(id);
    }

    public Email(String email) {
        this.email = email;
    }
}
