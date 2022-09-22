package vn.elca.training.model.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "groups")
@Getter
@Setter
public class Group extends SupEntity {

    @OneToOne
    private Employee groupLeader;

    public Group() {
    }

}
