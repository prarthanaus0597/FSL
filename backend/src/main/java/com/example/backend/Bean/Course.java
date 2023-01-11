package com.example.backend.Bean;

import jakarta.persistence.*;
import java.util.List;
@Entity
@Table(name ="course")  //  you can specify MySQL table name it is optional; by-default it takes class name as table name
public class Course {
    @Id
    @Column(name ="course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int course_id;

    @Column(name="course_code", nullable = false,unique = true)
    private String course_code;

    @Column(name="course_name",nullable = false)
    private String course_name;

    @Column(name="description")
    private String description;

    @Column(name="year", nullable = false)
    private int year;

    @Column(name="term", nullable = false)
    private int term;

    @Column(name="credits", nullable = false)
    private int credits;

    @Column(name="capacity", nullable = false)
    private int capacity;

    @ManyToOne
    @JoinColumn(name="faculty_id")
    private Employee empCourse;

//
//    @OneToMany(mappedBy = "courseschedule",fetch = FetchType.LAZY)
//    private List<CourseSchedule> courseScheduleList;


    public Course(int course_id,String course_name,String course_code, String description,int capacity ,int term,int year,int credits,Employee employee) {
        this.course_id=course_id;
        this.course_name = course_name;
        this.course_code=course_code;
        this.description=description;
        this.term=term;
        this.year=year;
        this.credits=credits;
        this.capacity = capacity;
        this.empCourse=employee;
    }

    public Course() {
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }
//-----------------------------------------------------------------
    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int  term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getCourse_code() {
        return course_code;
    }

    public void setCourse_code(String course_code) {
        this.course_code = course_code;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


//    public Employee getEmployee() {
//        return empCousre;
//    }
//
//    public void setEmpDepartment(Employee employee) {
//        this.empCousre = employee;
//    }
    public Employee getEmpCoure() {
        return empCourse;
    }

    public void setEmpCousre(Employee employee) {
        this.empCourse = empCourse;
    }


    @Override
    public String toString() {
        return "Course{" +
                "course_id=" + course_id +
                ", couse_name='" + course_name + '\'' +
                ", course_code='" + course_code + '\'' +
                ", description='" + description + '\'' +
                ", term='" + term + '\'' +
                ", year='" + year + '\'' +
                ", credits='" + credits + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}