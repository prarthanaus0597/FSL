package com.example.backend.Services;

import com.example.backend.Bean.Employee;
import com.example.backend.DAO.DAOImplement.EmployeeDAOImpl;

public class EmployeeServices {
    EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl(); //in DAO impl
    public boolean addEmployee(Employee employee)
    { //called from controller
        return employeeDAO.addEmployee(employee); //calls the DAO method
    }

    public Employee login(Employee emp)
    {
        return employeeDAO.login(emp);
    }

    public Employee getEmployeeDetailsById(Integer emp_id) {

        return employeeDAO.getEmployeeDetailsById(emp_id);
    }

}