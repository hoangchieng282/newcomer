package vn.elca.training.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.Status;
import vn.elca.training.model.exception.*;
import vn.elca.training.repository.EmployeeRepository;
import vn.elca.training.repository.GroupRepository;
import vn.elca.training.repository.ProjectRepository;
import vn.elca.training.service.ProjectService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author vlp
 */
@Service()
@Transactional(rollbackFor = Throwable.class)
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    EntityManager entityManager;
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    private Pattern digitPattern = Pattern.compile("-?\\d+(\\.\\d+)?");


    public List<Project> getAllProject() {
        return projectRepository.getAllProject();
    }

    @Override
    public Project getOneProject(Long id) {
        return projectRepository.getOneProject(id);
    }

    public Project updateProject(Project project) {
        if (!projectRepository.existsById(project.getId())) {
            throw new ProjectNotFoundException();
        }
        return projectRepository.save(project);
    }

    public Project save(Project project) throws StartDateAfterEndDateException, ProjectNumberAlreadyExistsException {
        if (project.getEndDate() != null && project.getStartDate().isAfter(project.getEndDate())) {
            throw new StartDateAfterEndDateException(project.getStartDate(), project.getEndDate());
        } else if (project.getId() == null && projectRepository.existsByProjectNumber(project.getProjectNumber())) {
            throw new ProjectNumberAlreadyExistsException(project.getProjectNumber());
        }
        return projectRepository.save(project);
    }

    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Boolean deleteProjectById(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new ProjectNotFoundException();
        } else if (!projectRepository.getOneProject(id).getStatus().toString().equals("NEW")) {
            throw new ProjectStatusNotValidException(projectRepository.getOneProject(id).getStatus());
        }
        projectRepository.deleteById(id);
        return true;
    }

    public Boolean deleteListProjectByIds(List<Long> ids) {
        List<Project> projects = projectRepository.findAllById(ids);
        for(Long id: ids) {
            if (!projectRepository.existsById(id)) {
                throw new ProjectNotFoundException();
            }else if (!projectRepository.getOneProject(id).getStatus().toString().equals("NEW")) {
                throw new ProjectStatusNotValidException(projectRepository.getOneProject(id).getStatus());
            }
        }
        projectRepository.deleteAll(projects);
        return true;
    }

    public List<Project> filter(String key, Status status) {
        Long projectNumber = digitPattern.matcher(key).matches() ? Long.valueOf(key) : -1;
        if (StringUtils.isBlank(key)) {
            return projectRepository.findAllByStatusOrderByProjectNumberAsc(status);
        }
        if (status != null) {
            return projectRepository.findAllByProjectNumberOrNameOrCustomerAndStatusOrderByProjectNumberAsc(projectNumber, key, key, status);
        }
        return projectRepository.findAllByProjectNumberOrNameOrCustomerOrderByProjectNumberAsc(projectNumber, key, key);
    }

    @Override
    public List<Project> findAllWithPagination(long page, long size) {
        return projectRepository.getWithPage(page, size);
    }

    public List<Project> filterWithPagination(String key, Status status, long page, long size) {
        return projectRepository.filterWithPaging(key, status, page, size);
    }
}
