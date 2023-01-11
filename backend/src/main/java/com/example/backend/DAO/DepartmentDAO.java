package com.example.backend.DAO;

import com.example.backend.Bean.Department;

import java.util.List;

public interface DepartmentDAO {
    boolean addDepartment(Department deptObj);
    List<Department> getDepartmentList();
}
