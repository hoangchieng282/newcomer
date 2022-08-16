package vn.elca.training.service;

import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;

import java.util.List;

public interface GroupService {

    List<Group> findAll();

    Group findById(Long id);

    Group findByGroupLeader(String groupByLeaderVisa);
}
