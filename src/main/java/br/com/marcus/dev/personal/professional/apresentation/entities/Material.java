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
@Table(name = "tb_material")
public class Material extends SuperEntity{

    private String name;
    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;

    public Material(UUID id) {
        super(id);
    }

    public Material(UUID id, String name, Part part) {
        super(id);
        this.name = name;
        this.part = part;
    }

    public Material(UUID id, String name) {
        super(id);
        this.name = name;
    }
}
