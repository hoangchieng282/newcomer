package vn.elca.training.repository.custom.employee;

import com.querydsl.jpa.impl.JPAQuery;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.QEmployee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    QEmployee employees = QEmployee.employee;

    @Override
    public List<Employee> findAllByVisa(List<String> visas) {
        return new JPAQuery<Employee>(em)
                .from(employees)
                .where(employees.visa.in(visas))
                .fetch();
    }
}
