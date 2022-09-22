package vn.elca.training.repository.custom.group;

import com.querydsl.jpa.impl.JPAQuery;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.QEmployee;
import vn.elca.training.model.entity.QGroup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class GroupRepositoryCustomImpl implements GroupRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    QGroup group = QGroup.group;
    QEmployee employee = QEmployee.employee;

    public Group findGroupByLeaderVisa(String visa) {
        return new JPAQuery<Group>(em)
                .from(group)
                .innerJoin(group.groupLeader, employee)
                .fetchJoin()
                .where(employee.visa.eq(visa))
                .fetchFirst();
    }
}
