package vn.elca.training.web;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.Status;
import vn.elca.training.service.EmployeeService;
import vn.elca.training.service.GroupService;
import vn.elca.training.service.ProjectService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author gtn
 */
@CrossOrigin()
@RestController
@RequestMapping("/project")

public class ProjectController extends AbstractApplicationController {


    @Autowired
    private ProjectService projectService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<ProjectDto> getAll() {
        return projectService.getAllProject()
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/edit")
    public ProjectDto getOne(@RequestParam Long id) {
        Project entity = projectService.getOneProject(id);

        ProjectDto dto = mapper.projectToProjectDto(entity);

        dto.setGroupLeader(entity.getGroup().getGroupLeader().getVisa());
        if (entity.getEmployees() != null) {
            dto.setEmployees(entity.getEmployees().stream().map(Employee::getVisa).collect(Collectors.joining(",")));
        }

        return dto;
    }

    @PostMapping("/edit")
    public void updateProject(@RequestBody ProjectDto projectDto, @RequestParam Long id) {
        Project project = mapper.projectDtoToProject(projectDto);
        project.setId(id);
        List<Employee> employees = employeeService.findByListVisa(Arrays.asList(projectDto.getEmployees().replaceAll("\\s+", "").toUpperCase().split(",")));
        project.setEmployees(employees);
        project.setGroup(groupService.findByGroupLeader(projectDto.getGroupLeader()));
        projectService.updateProject(project);

    }

    @PostMapping
    public void saveProject(@RequestBody ProjectDto projectDto) {
        Project project = mapper.projectDtoToProject(projectDto);
        List<Employee> employees = employeeService.findByListVisa(Arrays.asList(projectDto.getEmployees().replaceAll("\\s+", "").toUpperCase().split(",")));
        project.setEmployees(employees);
        project.setGroup(groupService.findByGroupLeader(projectDto.getGroupLeader()));
        projectService.save(project);
    }

    @DeleteMapping
    public void deleteProject(@RequestParam Long id) {
         projectService.deleteProjectById(id);
    }


    @DeleteMapping("/deleteList")
    public void deleteListProject(@RequestParam List<Long> ids) {
        projectService.deleteListProjectByIds(ids);
    }


    @GetMapping("/filter")
    public List<ProjectDto> filter(@RequestParam String keyword, @RequestParam String status) {
        return projectService.filter(keyword, StringUtils.isBlank(status) ? null : Status.valueOf(status))
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/page")
    public List<ProjectDto> findAllWithPagination(@RequestParam int page, @RequestParam int size) {
        return projectService.findAllWithPagination(page, size)
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/pageFilter")
    public List<ProjectDto> filterWithPagination(@RequestParam String keyword, @RequestParam String status, @RequestParam int page, @RequestParam int size) {
        return projectService.filterWithPagination(keyword, StringUtils.isBlank(status) ? null : Status.valueOf(status), page, size)
                .stream()
                .map(mapper::projectToProjectDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/status")
    public List<String> getStatus() {
        return Arrays.asList(Status.values().toString());
    }
}
