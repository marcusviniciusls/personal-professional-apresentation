package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_partner")
public class Partner extends SuperEntity{

    private String name;
    private String urlImage;
    @ManyToOne
    @JoinColumn(name = "branch_activity_id")
    private BranchActivity branchActivity;
    private String description;
}
