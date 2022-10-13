package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.OfficeLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_office")
public class Office extends SuperEntity{

    private String name;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private OfficeLevel officeLevel;

    public Office(UUID id) {
        super(id);
    }

    public Office(UUID id, String name, String description, OfficeLevel officeLevel) {
        super(id);
        this.name = name;
        this.description = description;
        this.officeLevel = officeLevel;
    }
}
