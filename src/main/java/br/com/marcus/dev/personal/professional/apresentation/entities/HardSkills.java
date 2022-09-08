package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.Level;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_hard_skills")
public class HardSkills extends SuperEntity{

    private String name;
    private String description;
    @Enumerated(EnumType.ORDINAL)
    private Level level;
    @ManyToMany
    @JoinTable(name = "tb_hardskills_languageprogrammer", joinColumns =
            {@JoinColumn(name = "hardskills_id")}, inverseJoinColumns =
            {@JoinColumn(name="languageprogrammer_id")})
    private List<LanguageProgrammer> listLanguage = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "tb_hardskills_framework", joinColumns =
            {@JoinColumn(name = "hardskills_id")}, inverseJoinColumns =
            {@JoinColumn(name="framework_id")})
    private List<Framework> listFramework = new ArrayList<>();

    public HardSkills(String name, String description, Level level) {
        this.name = name;
        this.description = description;
        this.level = level;
    }

    public void addListLanguageProgrammer(LanguageProgrammer languageProgrammer){
        this.listLanguage.add(languageProgrammer);
    }

    public void addListFramework(Framework framework){
        this.listFramework.add(framework);
    }
}
