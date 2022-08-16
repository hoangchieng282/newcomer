package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.entity.Group;
import vn.elca.training.service.EmployeeService;
import vn.elca.training.service.GroupService;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/group")
public class GroupController extends AbstractApplicationController{

    @Autowired
    private GroupService groupService;

    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public List<GroupDto> getAllGroupLeaderVisa(){
        return groupService.findAll()
                .stream()
                .map(mapper::groupToGroupDto)
                .collect(Collectors.toList());
    }




}
