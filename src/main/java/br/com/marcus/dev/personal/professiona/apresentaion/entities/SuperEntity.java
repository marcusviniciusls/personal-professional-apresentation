package br.com.marcus.dev.personal.professiona.apresentaion.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
public class SuperEntity {

    @Id
    @Type(type="org.hibernate.type.UUIDCharType")
    private UUID uuid;
    private LocalDateTime dateCreation;
    private LocalDateTime dateUpdate;
    private String userCreation;
    private String userUpdate;
    private boolean status;

    public SuperEntity() {
        uuid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        dateCreation = now;
        status = true;
    }
}
