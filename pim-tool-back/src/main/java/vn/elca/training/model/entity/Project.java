package vn.elca.training.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @author vlp
 */
@Entity
@Getter
@Setter
public class Project extends SupEntity {

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    private Group group;

    @Column(nullable = false)
    private Long projectNumber;

    @Column(nullable = false)
    private String projectName;

    @Column(nullable = false)
    private String customer;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column
    private LocalDate endDate;


    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "project_id"), inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;

    public Project(Group group, Long projectNumber, String name, String customer, Status status, LocalDate startDate, LocalDate endDate, Long version, List<Employee> employees) {
        this.group = group;
        this.customer = customer;
        this.projectNumber = projectNumber;
        this.projectName = name;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.version = version;
    }

    public Project() {
    }


}
