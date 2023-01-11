package com.example.backend.Services;

import com.example.backend.Bean.Course;
import com.example.backend.Bean.CourseSchedule;
import com.example.backend.DAO.DAOImplement.CourseDAOImpl;
import com.example.backend.DAO.DAOImplement.CourseScheduleDAOImpl;
import com.example.backend.DAO.DAOImplement.EmployeeDAOImpl;

import java.util.List;


public class CourseServices {
    CourseScheduleDAOImpl courseScheduleDAO = new CourseScheduleDAOImpl(); //in DAO impl
    CourseDAOImpl courseDAO = new CourseDAOImpl(); //in DAO impl
    public List<CourseSchedule> istaken(Integer cid, Integer eid ){ //called from controller
        return courseScheduleDAO.getCourseSchedules(cid,eid); //calls the DAO method
    }
    public boolean addCoursesToId(Integer cid, Integer eid ){ //called from controller
        return courseDAO.addCourseToId(cid,eid); //calls the DAO method
    }


}
