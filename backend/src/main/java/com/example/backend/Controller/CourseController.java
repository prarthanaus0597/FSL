package com.example.backend.Controller;

import com.example.backend.Bean.Course;

import com.example.backend.Bean.Employee;
import com.example.backend.DAO.CourseDAO;
import com.example.backend.DAO.DAOImplement.CourseDAOImpl;

import com.example.backend.Services.CourseServices;
import com.example.backend.Services.EmployeeServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/course")
public class CourseController {
    CourseDAO courseDAO = new CourseDAOImpl();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response add_Course(Course course) {
        System.out.printf(String.valueOf(course));
        if (courseDAO.addCourse(course)) {
            return Response.status(200).entity("Success").build();
        }

        return Response.status(400).entity("Failure while adding courses").build();
    }

    @GET
    @Path("/assign/{eid}/{cid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCoursestoID(@PathParam("cid") Integer cid,@PathParam("eid") Integer eid) {
        boolean val  = new CourseServices().addCoursesToId(cid,eid);
//        System.out.println("courses****");
        if (val == true)
            return Response.ok().build();
        else
            return Response.status(203).build();
    }

    @GET
    @Path("/courseadded/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response coursesadded(@PathParam("eid") Integer eid) {
        List<Course> courses = courseDAO.getCourseAdded(eid);
//        System.out.println("courses****");
        if (!courses.isEmpty())
            return Response.ok(courses).build();
        else
            return Response.status(400).build();
    }





    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_all_Course() {
        List<Course> courses = courseDAO.getCourseList();
//        System.out.printf("Hello world");
        return Response.status(200).entity(courses).build();
    }
}