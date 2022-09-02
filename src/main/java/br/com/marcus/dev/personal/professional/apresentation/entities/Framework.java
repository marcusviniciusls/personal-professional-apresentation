package br.com.marcus.dev.personal.professional.apresentation.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.websocket.server.ServerEndpoint;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_framework")
public class Framework extends SuperEntity{

    private String name;
    private String description;
    private String urlImage;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
