package br.com.marcus.dev.personal.professional.apresentation.entities;

import br.com.marcus.dev.personal.professional.apresentation.entities.enums.LevelCourse;
import br.com.marcus.dev.personal.professional.apresentation.entities.enums.StatusCourse;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tb_course_language_programmer", joinColumns =
            {@JoinColumn(name = "course_id")}, inverseJoinColumns =
            {@JoinColumn(name="language_programmer_id")})
    private List<LanguageProgrammer> listLanguage = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_course_framework", joinColumns =
            {@JoinColumn(name = "course_id")}, inverseJoinColumns =
            {@JoinColumn(name="framework_id")})
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

    public Course(UUID id) {
        super(id);
    }

    public void addListLanguage(LanguageProgrammer language){
        this.listLanguage.add(language);
    }

    public void addListFramework(Framework framework){
        this.listFramework.add(framework);
    }
}
