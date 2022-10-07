package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

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

    public Partner(UUID id) {
        super(id);
    }

    public Partner(UUID id, String name, String urlImage, BranchActivity branchActivity, String description) {
        super(id);
        this.name = name;
        this.urlImage = urlImage;
        this.branchActivity = branchActivity;
        this.description = description;
    }
}
