package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professiona.apresentaion.entities.enums.StatusCourse;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_courses")
public class Course extends SuperEntity{

    private String name;
    private String description;
    private BigDecimal duration;
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

    public Course(String name, String description, BigDecimal duration, LocalDate dateInitExpected, LocalDate dateFinishExpected, LocalDate dateInitReal, LocalDate dateFinishReal, String logoImage, StatusCourse statusCourse, LevelCourse levelCourse) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.dateInitExpected = dateInitExpected;
        this.dateFinishExpected = dateFinishExpected;
        this.dateInitReal = dateInitReal;
        this.dateFinishReal = dateFinishReal;
        this.logoImage = logoImage;
        this.statusCourse = statusCourse;
        this.levelCourse = levelCourse;
    }

    public void addListLanguage(LanguageProgrammer language){
        this.listLanguage.add(language);
    }

    public void addListFramework(Framework framework){
        this.listFramework.add(framework);
    }
}
