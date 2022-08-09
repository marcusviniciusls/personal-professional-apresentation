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
@Table(name = "tb_part")
public class Part extends SuperEntity{

    private String name;
    @Enumerated(EnumType.ORDINAL)
    private Level level;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
    @OneToMany(mappedBy = "part")
    private List<Material> listMaterial = new ArrayList<>();

    public Part(String name, Level level, Language language) {
        this.name = name;
        this.level = level;
        this.language = language;
    }

    public void addListMaterial(Material material){
        this.listMaterial.add(material);
    }
}
