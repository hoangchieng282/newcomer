package vn.elca.training.service;

import java.util.List;

import org.springframework.data.domain.Page;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.Status;

/**
 * @author vlp
 *
 */
public interface ProjectService {
    List<Project> getAllProject();

    Project getOneProject(Long id);

    Project save(Project project);

    Project updateProject(Project project);

    Boolean deleteProjectById(Long projectId);

    Boolean deleteListProjectByIds(List<Long> ids);

    List<Project> filter(String key, Status status);

    List<Project> findAllWithPagination(long offset, long pageSize);

    List<Project> filterWithPagination(String key, Status status, long offset, long pageSize);
}
