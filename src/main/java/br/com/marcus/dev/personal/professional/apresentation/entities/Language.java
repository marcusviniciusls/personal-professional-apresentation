package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    public Language(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public void addListPart(Part part){
        this.listPart.add(part);
    }
}
