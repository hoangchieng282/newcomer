package vn.elca.training.repository.custom.project;

import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.Status;

import java.util.List;

public interface ProjectRepositoryCustom {

    List<Project> findAllByProjectNumberOrNameOrCustomerAndStatusOrderByProjectNumberAsc(Long projectNumber, String name, String customer, Status status);

    List<Project> findAllByProjectNumberOrNameOrCustomerOrderByProjectNumberAsc(Long projectNumber, String name, String customer);

    List<Project> findAllByStatusOrderByProjectNumberAsc( Status status);

    List<Project> filterWithPaging(String key, Status status, long page, long size);

    List<Project> getAllProject();

    List<Project> getWithPage(long page, long size);

    Project getOneProject(Long id);


}
