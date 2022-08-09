package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_hard_skills")
public class HardSkills extends SuperEntity{

    private String description;
    @Enumerated(EnumType.ORDINAL)
    private Level level;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private LanguageProgrammer language;
    @ManyToOne
    @JoinColumn(name = "framework_id")
    private Framework framework;
}
