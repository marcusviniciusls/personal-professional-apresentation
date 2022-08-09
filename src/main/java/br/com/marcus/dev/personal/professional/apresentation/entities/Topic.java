package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_topic")
public class Topic extends SuperEntity{

    private String name;
    @ManyToOne
    @JoinColumn(name = "study_plan_id")
    private StudyPlan studyPlan;
}
