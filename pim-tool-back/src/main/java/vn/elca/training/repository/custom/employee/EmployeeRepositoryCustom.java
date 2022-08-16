package vn.elca.training.repository.custom.employee;

import vn.elca.training.model.entity.Employee;

import java.util.List;

public interface EmployeeRepositoryCustom {

    List<Employee> findAllByVisa(List<String> visas);
}
