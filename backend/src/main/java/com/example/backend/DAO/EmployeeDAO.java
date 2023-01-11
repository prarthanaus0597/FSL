package com.example.backend.DAO;


import com.example.backend.Bean.Employee;

import java.util.List;

public interface EmployeeDAO {

    boolean addEmployee(Employee employee);

    List<Employee> getEmployeeList();

    Employee login(Employee emp);

    Employee getEmployeeDetailsById(Integer emp_id);
}
