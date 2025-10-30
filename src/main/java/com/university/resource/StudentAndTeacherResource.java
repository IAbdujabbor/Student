package com.university.resource;

import com.university.entity.Student;
import com.university.entity.StudentAndTeacher;
import com.university.service.StudentAndTeacherService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/studentandteacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentAndTeacherResource {

    private final StudentAndTeacherService studentAndTeacherService;

    @Inject
    public StudentAndTeacherResource(StudentAndTeacherService studentAndTeacherService) {
        this.studentAndTeacherService = studentAndTeacherService;
    }

    //Read All
    @GET
    @Path("/getAll")
    public Response getAll() {
        studentAndTeacherService.getAll();
        return Response.ok().build();
    }

    //Create
    @POST
    @Path("/add")
    public Response add(StudentAndTeacher studentAndTeacher) {
        studentAndTeacherService.add(studentAndTeacher);
        return Response.ok().build();
    }

    //Remove
    @DELETE
    @Path("id/{id}")
    public Response delete(@PathParam("id") Long id) {
        studentAndTeacherService.deleteByIdStudentAndTeacher(id);
        return Response.status(Response.Status.OK).build();
    }


}


