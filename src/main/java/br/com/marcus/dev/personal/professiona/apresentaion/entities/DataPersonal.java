package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.MaritalStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_data_personal")
public class DataPersonal extends SuperEntity {

    private String fullName;
    private Byte age;
    private LocalDate birthDate;
    @Enumerated(EnumType.ORDINAL)
    private MaritalStatus maritalStatus;
    @OneToMany(mappedBy = "dataPersonal")
    private List<Telephone> listTelephone = new ArrayList<>();
    @OneToMany(mappedBy = "dataPersonal")
    private List<Email> listEmail = new ArrayList<>();

    public void addListTelephone(Telephone telephone){
        this.listTelephone.add(telephone);
    }

    public void addListEmail(Email email){
        this.listEmail.add(email);
    }
}