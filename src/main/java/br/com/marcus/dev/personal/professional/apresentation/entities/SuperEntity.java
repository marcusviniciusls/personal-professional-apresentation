package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class SuperEntity {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID id;
    private LocalDateTime dateIssue;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private String userCreation;
    private String userUpdate;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public SuperEntity() {
        id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        dateIssue = now;
        dateCreation = now;
        status = true;
    }

    public SuperEntity(UUID id) {
        this.id = id;
        LocalDateTime now = LocalDateTime.now();
        dateIssue = now;
        dateCreation = now;
        this.status = true;
    }
}
