package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
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

    public Part(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public Part(UUID id, String name, Level level) {
        super(id);
        this.name = name;
        this.level = level;
    }

    public Part(UUID id, String name, Level level, Language language) {
        super(id);
        this.name = name;
        this.level = level;
        this.language = language;
    }

    public Part(UUID id) {
        super(id);
    }

    public void addListMaterial(Material material){
        this.listMaterial.add(material);
    }
}
