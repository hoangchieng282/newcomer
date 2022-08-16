package vn.elca.training.service;

import vn.elca.training.model.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee findByVisa(String visa);

    List<Employee> findByListVisa(List<String> visa);

    List<Employee> findAll();


}
