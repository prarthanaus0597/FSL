package com.example.backend.Controller;

import com.example.backend.Bean.Course;

import com.example.backend.Bean.CourseSchedule;
import com.example.backend.DAO.CourseScheduleDAO;

import com.example.backend.DAO.DAOImplement.CourseScheduleDAOImpl;
import com.example.backend.Services.CourseServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;



@Path("/courseschedule")
public class CourseScheduleController {
    CourseScheduleDAO courseScheduleDAO = new CourseScheduleDAOImpl();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response add_CourseSchedule(CourseSchedule courseSchedule) {
        System.out.printf(String.valueOf(courseSchedule));
        if (courseScheduleDAO.addCourseSchedule(courseSchedule)) {
            return Response.status(200).entity("Success").build();
        }

        return Response.status(400).entity("Failure while adding Courseschedule").build();
    }
    @GET
    @Path("/istaken/{eid}/{cid}")
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIstaken(@PathParam("cid") Integer cid,@PathParam("eid") Integer eid) {
        List<CourseSchedule> c= new CourseServices().istaken(cid,eid);
        System.out.printf(String.valueOf(c));
//        if (!(courseDAO.getCourses(cid,eid)).isEmpty()) {
//        List<CourseSchedule> courseSchedules = courseScheduleDAO.getCourseSchedules(cid,eid);
        if (!((courseScheduleDAO.getCourseSchedules(cid,eid)).isEmpty())) {
        return Response.status(200).entity(c).build();
        }

        return Response.status(400).entity("No Conflict !!").build();
    }
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_all_CourseSchedule() {
        List<CourseSchedule> courseSchedules = courseScheduleDAO.getCourseScheduleList();
//        System.out.printf("Hello world");
        return Response.status(200).entity(courseSchedules).build();
    }
//    @GET
//    @Path("/getall")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response get_CourseSchedule() {
//
//        List<CourseSchedule> courseSchedules = courseScheduleDAO.getCourseScheduleToView();
////        System.out.printf("Hello world");
//        return Response.status(200).entity(courseSchedules).build();
//    }



}