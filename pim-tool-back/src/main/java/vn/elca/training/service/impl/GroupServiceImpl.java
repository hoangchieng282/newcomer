package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.repository.GroupRepository;
import vn.elca.training.service.GroupService;

import java.util.List;

@Service()
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public Group findById(Long id) {
        return groupRepository.findById(id).orElse(null);
    };


    public Group findByGroupLeader(String groupByLeaderVisa) {
        return groupRepository.findGroupByLeaderVisa(groupByLeaderVisa);
    }
}
