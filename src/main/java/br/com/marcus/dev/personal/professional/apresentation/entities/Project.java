package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_project")
public class Project extends SuperEntity{

    private String title;
    private String description;
    private String linkGitHub;
    private String linkYoutube;
    @ManyToMany
    @JoinTable(name = "tb_project_language_programmer", joinColumns =
            {@JoinColumn(name = "project_id")}, inverseJoinColumns =
            {@JoinColumn(name="language_programmer_id")})
    private List<LanguageProgrammer> listLanguageProgrammer = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "tb_project_framework", joinColumns =
            {@JoinColumn(name = "project_id")}, inverseJoinColumns =
            {@JoinColumn(name="framework_id")})
    private List<Framework> listFramework = new ArrayList<>();

    public Project(UUID id) {
        super(id);
    }

    public void addListLanguageProgrammer(LanguageProgrammer languageProgrammer){
        this.listLanguageProgrammer.add(languageProgrammer);
    }

    public void addListFramework(Framework framework){
        this.listFramework.add(framework);
    }
}
