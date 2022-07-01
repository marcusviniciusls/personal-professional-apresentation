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

    public void addListMaterial(Material material){
        this.listMaterial.add(material);
    }
}
