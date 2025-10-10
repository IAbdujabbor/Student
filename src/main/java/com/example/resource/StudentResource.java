package com.example.resource;

import com.example.entity.Student;
import com.example.service.StudentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class    StudentResource {

  @Inject
    StudentService studentService;

    @POST
    @Path("/create")
    public Student create(Student student) {
        return studentService.create(student.getName(), student.getAge());
    }


    @GET
    @Path("/getAll")
    public Object getAll() {
        return studentService.getAll();
    }

    @PUT

    @Path("/update/{id}")
    public Student update(@PathParam("id") Long id , Student student) {

        return studentService.update(id , student.getName());
    }

    @GET
    @Path("/getStudent/{id}")
    public Student getStudent(@PathParam("id")Long id){
        return studentService.getStudent(id);
    }


    @DELETE
    @Path("/delete/{id}")
    public Student remove(@PathParam("id")Long id) {
        return studentService.delete(id);
    }

    @GET
    @Path("/SearchStudent/{name}")
    public Student SearchStudent(@PathParam("name")String name) {

        return studentService.StudentSearch(name);
    }


}
