package vn.elca.training.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.elca.training.model.dto.EmployeeDto;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.entity.Employee;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.entity.Project;
import vn.elca.training.model.entity.Status;
import vn.elca.training.repository.GroupRepository;

/**
 * @author gtn
 */
@Component
public class ApplicationMapper {


    public ApplicationMapper() {

    }

    public ProjectDto projectToProjectDto(Project entity) {
        ProjectDto dto = new ProjectDto();
        dto.setId(entity.getId());
        dto.setProjectNumber(entity.getProjectNumber());
        dto.setProjectName(entity.getProjectName());
        dto.setCustomer(entity.getCustomer());
        dto.setStatus(entity.getStatus().toString());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setVersion(entity.getVersion());
        return dto;
    }

    public Project projectDtoToProject(ProjectDto projectDto) {
        Project project = new Project();
        project.setProjectNumber(projectDto.getProjectNumber());
        project.setCustomer(projectDto.getCustomer());
        project.setProjectName(projectDto.getProjectName());
        project.setEndDate(projectDto.getEndDate());
        project.setStartDate(projectDto.getStartDate());
        project.setStatus(Status.valueOf(projectDto.getStatus().toUpperCase()));
        project.setVersion(projectDto.getVersion());
        return project;
    }

    public GroupDto groupToGroupDto(Group group) {
        GroupDto groupDto = new GroupDto();
        groupDto.setId(group.getId());
        groupDto.setLeaderVisa(group.getGroupLeader().getVisa());
        return groupDto;
    }

    public Group groupDtoToGroup(GroupDto groupDto) {
        Group group = new Group();
        group.setVersion(groupDto.getVersion());
        return group;
    }

    public Employee employeeDtoToEmployee(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setBirthDate(employeeDto.getBirthDate());
        employee.setVersion(employeeDto.getVersion());
        employee.setLastName(employeeDto.getLastName());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setVisa(employeeDto.getVisa());
        return employee;
    }

    public EmployeeDto employeeToEmployeeDto(Employee employee) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setBirthDate(employee.getBirthDate());
        employeeDto.setVersion(employee.getVersion());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setVisa(employee.getVisa());
        return employeeDto;
    }


}
