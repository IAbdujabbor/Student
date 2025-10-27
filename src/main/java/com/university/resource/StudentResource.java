package com.university.resource;

import com.university.dto.StudentDto;
import com.university.entity.Student;
import com.university.service.StudentService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/students")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class    StudentResource {

  private final StudentService studentService;



    @Inject
    public StudentResource(StudentService studentService  ) {
    this.studentService = studentService;}



    @POST
    @Path("/create")
    public StudentDto create(Student student) {
     //   return studentService.create(student.getName(), student.getAge());
      return null;
    }


    @GET
    @Path("findById/{id}")
    public Response findById(@PathParam("id") Long id) {

      return studentService.getById(id)
              .map(student-> Response.ok(student).build())
              .orElse(Response.status(Response.Status.NOT_FOUND)
              .entity("Student with id" + id + " not found" )
                .build());

    }


    @GET
    @Path("/getAll")
    public List<Student> getAll(){return studentService.getAll();}


//    @GET
//    @Path("/getAll")
//    public Object getAll() {
//        return studentService.getAll();
//    }

    @PUT
    @Path("/update/{id}")
    public StudentDto update(@PathParam("id") Long id , Student student) {
      return null;//return studentService.update(id , student.getName());
    }

    @GET
    @Path("/getStudent/{id}")
    public Student getStudent(@PathParam("id")Long id){return studentService.getStudent(id);}

  // CREATE (INSERT)
  @POST
  @Transactional
  @Path("/addStudent")
  public Response addStudent(Student student) {
    studentService.addStudent(student);
    return Response
            .status(Response.Status.CREATED)
            .entity(student)
            .build();
  }

    @DELETE
    @Path("/delete/{id}")
    public Student remove(@PathParam("id")Long id) {
        return studentService.delete(id);
    }

    @GET
    @Path("/SearchStudent/{name}")
     public StudentDto searchStudent(@PathParam("name") String name) {return studentService.studentSearch(name);}



}
