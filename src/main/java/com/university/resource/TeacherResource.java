package com.university.resource;


import com.university.dto.request.TeacherDtoRequest;
import com.university.dto.response.TeacherDtoResponse;
import com.university.entity.Teacher;
import com.university.repository.TeacherRepository;
import com.university.response.ResponseData;
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
    public ResponseData<List<TeacherDtoResponse>> getAllTeachers() {
        try {
            var teacherList = teacherService.getAllTeachers();
            return new ResponseData<>(teacherList, "Successfully retrieved all teachers", 200);
        } catch (Exception e) {
            return new ResponseData<>("Error retrieving all teachers", 500);
        }

    }

    //Read By id
    @GET
    @Path("/getById/{id}")
    public ResponseData<TeacherDtoResponse> getTeacherById(@PathParam("id") Long id) {
        try {
            var teacherExist = teacherService.getTeacherById(id)
                    .orElseThrow(() -> new NotFoundException("Teacher not found with id: " + id));
            return new ResponseData<>(teacherExist, "Successfully retrieved teacher", 200);
        } catch (NotFoundException e) {
            return new ResponseData<>(e.getMessage(), 404);
        } catch (Exception e) {
            return new ResponseData<>("Error retrieving teacher " + e.getMessage(), 500);
        }
    }

    //Create
    @POST
    @Transactional
    @Path("/add")
    public ResponseData<TeacherDtoResponse> addTeacher(TeacherDtoRequest teacher) {
        try {
            TeacherDtoResponse created = teacherService.addTeacher(teacher);
            return new ResponseData<>(created, "Teacher successfully added", 201);
        } catch (Exception e) {
            return new ResponseData<>("Error updating teacher:" + e.getMessage(), 500);
        }
    }

    //Edit
    @PUT
    @Path("edit/{id}")
    @Transactional
    public ResponseData<TeacherDtoResponse> editTeacher(@PathParam("id") Long id, TeacherDtoRequest teacherParameter) {
        try {
            TeacherDtoResponse edited = teacherService.updateTeacher(id, teacherParameter);
            return new ResponseData<>(edited, "Teacher successfully updated", 200);
        } catch (NotFoundException e) {
            return new ResponseData<>(e.getMessage(), 404);
        } catch (Exception e) {
            return new ResponseData<>("Error updating teacher:" + e.getMessage(), 500);
        }
    }

    //Delete
    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public ResponseData<Void> deleteTeacherById(@PathParam("id") Long id) {
        try {
            teacherService.deleteTeacher(id);
            return new ResponseData<>(null, "Teacher successfully deleted", 200);
        } catch (NotFoundException e) {
            return new ResponseData<>(e.getMessage(), 404);
        } catch (Exception e) {
            return new ResponseData<>("Error deleting teacher:" + e.getMessage(), 500);
        }
    }


}
