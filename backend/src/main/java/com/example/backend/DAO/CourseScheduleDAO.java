package com.example.backend.DAO;


import com.example.backend.Bean.Course;
import com.example.backend.Bean.CourseSchedule;

import java.util.List;

public interface CourseScheduleDAO {
    boolean addCourseSchedule(CourseSchedule courseSchedule);
    List<CourseSchedule> getCourseScheduleList();
    List<CourseSchedule> getCourseSchedules(Integer cid, Integer eid);

//    List<CourseSchedule> getCourseScheduleToView();
}
