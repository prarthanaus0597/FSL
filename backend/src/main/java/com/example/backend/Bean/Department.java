package com.example.backend.Bean;
//import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name ="department")  //  you can specify MySQL table name it is optional; by-default it takes class name as table name
public class Department {
    @Id
    @Column(name ="dept_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int departmentID;

    @Column(name="dname")
    private String departmentName;

    @Column(name="capacity")
    private int capacity;



    public Department(String departmentName, int capacity) {
        this.departmentName = departmentName;
        this.capacity = capacity;

    }

    public Department() {
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
//
//    public List<Employee> getEmployeeList() {
//        return employeeList;
//    }
//
//    public void setEmployeeList(List<Employee> employeeList) {
//        this.employeeList = employeeList;
//    }
    @Override
    public String toString() {
        return "Department{" +
                "departmentID=" + departmentID +
                ", departmentName='" + departmentName + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}