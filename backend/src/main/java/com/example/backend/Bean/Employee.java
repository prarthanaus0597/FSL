package com.example.backend.Bean;
import java.io.Serializable;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "employee")
public class Employee implements Serializable {
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int emp_id;

    @Column(name = "fname", nullable = false)
    private String fname;

    @Column(name= "lname")
    private String lname;

    @Column(name ="email", nullable = false,unique = true)
    private String email;

    @Column(name ="title")
    private String title;
    @Column(name ="password")
    private String password;
    @Column(name ="photo_path")
    private String photo_path;

    @Column(name ="departmentName")
    private String departmentName;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="departmentID")
//    private Department empDepartment;

    public Employee(){

    }

    public Employee(int employeeID, String fname, String lname,String email,String title,String photo_path,String password,String departmentName) {
        this.emp_id = employeeID;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.title = title;
        this.photo_path = photo_path;
        this.password=password;
        this.departmentName=departmentName;
//        this.empDepartment = empDepartment;
    }

    public int getEmployeeID() {
        return emp_id;
    }

    public void setEmployeeID(int employeeID) {
        this.emp_id = employeeID;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto_path() {
        return photo_path;
    }

    public void setPhoto_path(String photo_path) {
        this.photo_path = photo_path;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getDepartmentName () {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

//    public Department getDepartment() {
//        return empDepartment;
//    }
//
//    public void setDepartment(Department department) {
//        this.empDepartment = department;
//    }
//
//    public Department getEmpDepartment() {
//        return empDepartment;
//    }
//
//    public void setEmpDepartment(Department empDepartment) {
//        this.empDepartment = empDepartment;
//    }

    // to string to print object as a string otherwise it returns object Hashcode
    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + emp_id +
                ", employeeFname='" + fname + '\'' +
                ", employeeLname='" + lname + '\'' +
                ", employeeEmail='" + email + '\'' +
                ", employeeTitle='" + title + '\'' +
                ", photoPath='" + photo_path + '\'' +
                ", password='" + password + '\'' +
                ", departmentName=" + departmentName +
//                ", empDepartment='" + empDepartment + '\'' +
                '}';
    }
}
