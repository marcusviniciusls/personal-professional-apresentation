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
@Table(name = "tb_language_programmer")
public class LanguageProgrammer extends SuperEntity{

    private String name;
    private String description;
    private String urlImage;

    public LanguageProgrammer(UUID id) {
        super(id);
    }

    public LanguageProgrammer(UUID id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    public LanguageProgrammer(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
