package com.university.resource;


import com.university.dto.request.StudentDtoRequest;
import com.university.dto.response.StudentDtoResponse;
import com.university.entity.Student;
import com.university.response.ResponseData;
import com.university.service.StudentService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/student")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StudentResource {

    private final StudentService studentService;


    @Inject
    public StudentResource(StudentService studentService) {
        this.studentService = studentService;
    }

    // Read All
    @GET
    @Path("/getAll")
    public ResponseData<List<StudentDtoResponse>> getAll() {
        try {
            List<StudentDtoResponse> student = studentService.getAll();
            return new ResponseData<>(student, "Successfully retrieved all students", 200);

        } catch (Exception e) {
            return new ResponseData<>(e.getMessage(), 500);
        }
    }


    //Read By id
    @GET
    @Path("getById/{id}")
    public ResponseData<StudentDtoResponse> getStudentById(@PathParam("id") Long id) {
        try {
            StudentDtoResponse student = studentService.getByIdStudent(id)
                    .orElseThrow(() -> new NotFoundException("Student not found with id: " + id));
            return new ResponseData<>(student, "Student retrieved successfull", 200);
        } catch (NotFoundException e) {
            return new ResponseData<>(e.getMessage(), 404);
        } catch (Exception e) {
            return new ResponseData<>("Error fetching student" + e.getMessage(), 500);
        }
    }


    // CREATE (INSERT)
    @POST
    @Transactional
    @Path("/add")
    public ResponseData<StudentDtoResponse> addStudent(StudentDtoRequest student) {
       try{StudentDtoResponse  created = studentService.addStudent(student);
        return new  ResponseData<>(created, "Student successfully added",201);
       } catch (Exception e) {
           return new ResponseData<>("Error creating student " + e.getMessage() ,500);
       }
    }

    //Edit
    @PUT
    @Path("/edit/{id}")
    @Transactional
    public ResponseData<StudentDtoResponse> editStudent(@PathParam("id") Long id, StudentDtoRequest student) {
        try{
            StudentDtoResponse edited =studentService.updateStudent(id,student );
            return new ResponseData<>(edited,"Student successfully edited",200);
        }catch (NotFoundException e){
            return new ResponseData<>(e.getMessage(),404);
        }catch (Exception e){
            return new ResponseData<>("Error editing student " + e.getMessage(),500);
        }

    }


    //Remove
    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public ResponseData<Void> deleteStudent(@PathParam("id") Long id) {
        try {
        studentService.deleteStudent(id);
        return new ResponseData<>(null, "Student successfully deleted",200);

        }catch (NotFoundException e){
            return new ResponseData<>(e.getMessage(),404);
        }catch (Exception e){
            return new ResponseData<>("Error deleting student"+ e.getMessage(),500);
        }
    }


//    //Search
//    @GET
//    @Path("/SearchStudent/{name}")
//    public StudentDto searchStudent(@PathParam("name") String name) {
//        return studentService.studentSearch(name);
//    }


}
