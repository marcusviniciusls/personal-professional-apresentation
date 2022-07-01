package br.com.marcus.dev.personal.professiona.apresentaion.entities;

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
@Table(name = "tb_material")
public class Material extends SuperEntity{

    private String name;
    @ManyToOne
    @JoinColumn(name = "part_id")
    private Part part;
}
