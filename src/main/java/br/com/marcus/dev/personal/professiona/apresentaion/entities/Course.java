package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.StatusCourse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_courses")
public class Course extends SuperEntity{

    private String name;
    private String description;
    private Double duration;
    private LocalDate dateInitExpected;
    private LocalDate dateFinishExpected;
    private LocalDate dateInitReal;
    private LocalDate dateFinishReal;
    private String logoImage;
    @Enumerated(EnumType.ORDINAL)
    private StatusCourse statusCourse;
    @Enumerated(EnumType.ORDINAL)
    private LevelCourse levelCourse;
    @OneToMany(mappedBy = "course")
    private List<LanguageProgrammer> listLanguage = new ArrayList<>();
    @OneToMany(mappedBy = "course")
    private List<Framework> listFramework = new ArrayList<>();

    public void addListLanguage(LanguageProgrammer language){
        this.listLanguage.add(language);
    }

    public void addListFramework(Framework framework){
        this.listFramework.add(framework);
    }
}
