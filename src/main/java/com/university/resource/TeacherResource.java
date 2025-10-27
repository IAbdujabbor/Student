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

@Path("/teachers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class TeacherResource {

    private final TeacherRepository teacherRepository;
    private final TeacherService teacherService;

    @Inject
    public TeacherResource(TeacherRepository teacherRepository, TeacherService teacherService) {
    this.teacherRepository = teacherRepository;
        this.teacherService = teacherService;
    }

    @POST
    @Transactional
    @Path("/addTeacher")
    public Response addTeacher(Teacher teacher) {
        teacherService.addTeacher(teacher);
        return Response
                .status(Response.Status.CREATED)
                .entity(teacher)
                .build();
    }

    @GET
    @Path("/getAll")
    public List<Teacher> getAllTeachers() {
        return  teacherService.getAllTeachers();
//                .stream()
//                .map(s->Response.status(200)
//                        .build());
    }


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
