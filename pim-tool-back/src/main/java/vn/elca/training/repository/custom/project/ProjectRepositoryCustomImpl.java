package vn.elca.training.repository.custom.project;

import com.querydsl.jpa.impl.JPAQuery;
import org.apache.commons.lang3.StringUtils;
import vn.elca.training.model.entity.*;
import vn.elca.training.model.entity.QEmployee;
import vn.elca.training.model.entity.QGroup;
import vn.elca.training.model.entity.QProject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.regex.Pattern;

public class ProjectRepositoryCustomImpl implements ProjectRepositoryCustom {
    @PersistenceContext
    private EntityManager em;
    QProject projects = QProject.project;

    QGroup groups = QGroup.group;

    QEmployee employees = QEmployee.employee;

    private Pattern digitPattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public List<Project> findAllByProjectNumberOrNameOrCustomerAndStatusOrderByProjectNumberAsc(Long projectNumber, String name, String customer, Status status) {
        return new JPAQuery<Project>(em)
                .from(projects)
                .where((projects.projectNumber.eq(projectNumber).or(projects.projectName.eq(name)).or(projects.customer.eq(customer))).and(projects.status.eq(status)))
                .orderBy(projects.projectNumber.asc())
                .fetch();
    }

    public List<Project> findAllByProjectNumberOrNameOrCustomerOrderByProjectNumberAsc(Long projectNumber, String name, String customer) {
        return new JPAQuery<Project>(em)
                .from(projects)
                .where(projects.projectNumber.eq(projectNumber).or(projects.projectName.eq(name)).or(projects.customer.eq(customer)))
                .orderBy(projects.projectNumber.asc())
                .fetch();
    }

    public List<Project> findAllByStatusOrderByProjectNumberAsc(Status status) {
        return new JPAQuery<Project>(em)
                .from(projects)
                .where(projects.status.eq(status))
                .orderBy(projects.projectNumber.asc())
                .fetch();
    }

    @Override
    public List<Project> getAllProject() {
        return new JPAQuery<Project>(em)
                .from(projects)
                .innerJoin(projects.group, groups)
                .fetchJoin()
                .where(projects.group.eq(groups))
                .orderBy(projects.projectNumber.asc())
                .fetch();
    }

    @Override
    public List<Project> getWithPage(long page, long size) {
        return new JPAQuery<Project>(em)
                .from(projects)
                .innerJoin(projects.group, groups)
                .fetchJoin()
                .where(projects.group.eq(groups))
                .orderBy(projects.projectNumber.asc())
                .limit(size)
                .offset(page*size)
                .fetch();
    }

    @Override
    public List<Project> filterWithPaging(String key, Status status, long page, long size) {
        Long projectNumber = digitPattern.matcher(key).matches() ? Long.valueOf(key) : -1;


        if (StringUtils.isBlank(key)) {
            return new JPAQuery<Project>(em)
                    .from(projects)
                    .where(projects.status.eq(status))
                    .orderBy(projects.projectNumber.asc())
                    .limit(size)
                    .offset(page*size)
                    .fetch();
        }
        else if (status != null) {
            return new JPAQuery<Project>(em)
                    .from(projects)
                    .where((projects.projectNumber.eq(projectNumber).or(projects.projectName.eq(key)).or(projects.customer.eq(key))).and(projects.status.eq(status)))
                    .orderBy(projects.projectNumber.asc())
                    .limit(size)
                    .offset(page*size)
                    .fetch();
        }
        else {
            return new JPAQuery<Project>(em)
                    .from(projects)
                    .where(projects.projectNumber.eq(projectNumber).or(projects.projectName.eq(key)).or(projects.customer.eq(key)))
                    .orderBy(projects.projectNumber.asc())
                    .limit(size)
                    .offset(page*size)
                    .fetch();
        }
    }

    @Override
    public Project getOneProject(Long id) {
        Project project = new JPAQuery<Project>(em)
                .from(projects)
                .where(projects.id.eq(id))
                .innerJoin(projects.group, groups)
                .fetchJoin()
                .fetchFirst();
        if (project.getEmployees().size() == 0) {
            return project;
        }
        else {
            return new JPAQuery<Project>(em)
                    .from(projects)
                    .where(projects.id.eq(id))
                    .innerJoin(projects.group, groups)
                    .fetchJoin()
                    .leftJoin(projects.employees, employees)
                    .fetchJoin()
                    .where(employees.in(projects.employees))
                    .fetchFirst();
        }
    }
}
