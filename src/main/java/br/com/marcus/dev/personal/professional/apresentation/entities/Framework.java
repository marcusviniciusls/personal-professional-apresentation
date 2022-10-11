package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_framework")
public class Framework extends SuperEntity{

    private String name;
    private String description;
    private String urlImage;

    public Framework(UUID id) {
        super(id);
    }

    public Framework(UUID id, String name, String description, String urlImage) {
        super(id);
        this.name = name;
        this.description = description;
        this.urlImage = urlImage;
    }
}
