package vn.elca.training.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;


@Entity
@Getter
@Setter
public class Employee extends SupEntity implements Serializable  {

    @Column(nullable = false)
    @Length(max = 3)
    private String visa;

    @Column(nullable = false)
    @Length(max = 50)
    private String firstName;

    @Column(nullable = false)
    @Length(max = 50)
    private String lastName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects;

    public Employee() {
    }


}
