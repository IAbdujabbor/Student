package com.university.resource;


import com.university.entity.Teacher;
import com.university.repository.TeacherRepository;
import com.university.service.TeacherService;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/teacher")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class TeacherResource {


    private final TeacherService teacherService;

    @Inject
    public TeacherResource(TeacherService teacherService) {

        this.teacherService = teacherService;
    }

    //Read All
    @GET
    @Path("/getAll")
    public Response getAllTeachers() {
        teacherService.getAllTeachers();
        return Response.ok(teacherService.getAllTeachers()).build();
    }

    @GET
    @Path("/getById/{id}")
    public Response getTeacherById(@PathParam("id") Long id) {
        return teacherService.getTeacherById(id)
                .map(teacherExist -> Response.ok(teacherExist).build())
                .orElse(Response.status(Response.Status.NOT_FOUND)
                        .entity("Teacher with ID " + id + " not found")
                        .build());
    }

    //Create
    @POST
    @Transactional
    @Path("/add")
    public Response addTeacher(Teacher teacher) {
        teacherService.addTeacher(teacher);
        return Response
                .status(Response.Status.CREATED)
                .entity(teacher)
                .build();
    }

    //Delete
    @DELETE
    @Path("/deleteById/{id}")
    public Response deleteTeacherById(@PathParam("id") Long id) {
        teacherService.deleteTeacher(id);
        return Response
                .status(Response.Status.OK)
                .entity(id)
                .build();
        //teacherService.deleteTeacher(id);
    }


}
