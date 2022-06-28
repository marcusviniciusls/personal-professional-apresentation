package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_soft_skills")
public class SoftSkills extends SuperEntity {

    private String name;
    private boolean statusHas;
}
