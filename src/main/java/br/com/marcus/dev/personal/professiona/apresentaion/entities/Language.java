package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_language")
public class Language extends SuperEntity{

    private String name;
    @Enumerated(EnumType.ORDINAL)
    private Level level;
    @OneToMany(mappedBy = "language")
    private List<Part> listPart = new ArrayList<>();

    public void addListPart(Part part){
        this.listPart.add(part);
    }
}
