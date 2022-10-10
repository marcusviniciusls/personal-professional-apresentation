package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_data_personal")
public class DataPersonal extends SuperEntity {

    private String fullName;
    private Integer age;
    private LocalDate birthDate;
    @Enumerated(EnumType.ORDINAL)
    private MaritalStatus maritalStatus;
    @OneToMany(mappedBy = "dataPersonal")
    private List<Telephone> listTelephone = new ArrayList<>();
    @OneToMany(mappedBy = "dataPersonal")
    private List<Email> listEmail = new ArrayList<>();

    public DataPersonal(String fullName, Integer age, LocalDate birthDate, MaritalStatus maritalStatus) {
        this.fullName = fullName;
        this.age = age;
        this.birthDate = birthDate;
        this.maritalStatus = maritalStatus;
    }

    public DataPersonal(UUID id) {
        super(id);
    }

    public void addListTelephone(Telephone telephone){
        this.listTelephone.add(telephone);
    }

    public void addListEmail(Email email){
        this.listEmail.add(email);
    }
}
