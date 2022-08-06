package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "tb_branch_activity")
public class BranchActivity extends SuperEntity{

    @Column(unique=true)
    private String name;
    @OneToMany(mappedBy = "branchActivity")
    private List<Partner> listPartner = new ArrayList<>();

    public BranchActivity(String name) {
        this.name = name;
    }

    public void addListPartner(Partner partner){
        this.listPartner.add(partner);
    }
}