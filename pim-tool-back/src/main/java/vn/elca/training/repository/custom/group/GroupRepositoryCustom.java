package vn.elca.training.repository.custom.group;

import vn.elca.training.model.entity.Group;

public interface GroupRepositoryCustom {
    Group findGroupByLeaderVisa(String visa);
}
