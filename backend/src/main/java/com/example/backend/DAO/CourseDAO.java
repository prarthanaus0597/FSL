package com.example.backend.DAO;

import com.example.backend.Bean.Course;

import java.util.List;

public interface CourseDAO {

    boolean addCourse(Course course);
    List<Course> getCourseList();
    List<Course> getCourseAdded(Integer eid);
boolean addCourseToId (Integer id,Integer c_id);
}