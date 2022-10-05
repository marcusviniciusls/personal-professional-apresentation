package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_topic")
public class Topic extends SuperEntity{

    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "study_plan_id")
    private StudyPlan studyPlan;

    public Topic(String name) {
        this.name = name;
    }

    public Topic(UUID id) {
        super(id);
    }
}
