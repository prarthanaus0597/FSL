package com.example.backend.Controller;

//import com.example.backend.Bean.Department;
import com.example.backend.Bean.Employee;
//import com.example.backend.DAO.DAOImplement.DepartmentDAOImpl;
import com.example.backend.DAO.DAOImplement.EmployeeDAOImpl;
//import com.example.backend.DAO.DepartmentDAO;
import com.example.backend.DAO.EmployeeDAO;
import com.example.backend.Services.EmployeeServices;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.net.URISyntaxException;
import java.util.List;

@Path("/employee")
public class EmployeeController {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response add_Employee(Employee employee) {
        System.out.printf(String.valueOf(employee));
        if (employeeDAO.addEmployee(employee)) {
            return Response.status(200).entity("Success").build();
        }

        return Response.status(400).entity("Failure while adding employee").build();
    }
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response loginFunc(Employee emp) throws URISyntaxException {
        Employee e = new EmployeeServices().login(emp);

        if (e == null) {
            return Response.status(203).build();
        }
        else if (e.getPassword().equals(emp.getPassword())) {

//            System.out.println(e.getEmployee_id());

//            return Response.ok().build();
            return Response.ok().entity(e).build();
        }
        else {
            return Response.status(203).build();
        }
    }



    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get_all_Employee() {
        List<Employee> employees = employeeDAO.getEmployeeList();
//        System.out.printf("Hello world");
        return Response.status(200).entity(employees).build();
    }


    @GET
    @Path("/getDetails/{eid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeDetails(@PathParam("eid") Integer eid) {
        Employee e = new EmployeeServices().getEmployeeDetailsById(eid);
            if(e==null)
            {
                return Response.status(203).build();
            }
            else
            return Response.status(200).entity(e).build();


    }
}