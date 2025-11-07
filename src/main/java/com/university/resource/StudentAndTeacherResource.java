package com.university.resource;

import com.university.dto.request.StudentAndTeacherDtoRequest;
import com.university.dto.response.StudentAndTeacherDtoResponse;
import com.university.entity.StudentAndTeacher;
import com.university.mapper.StudentAndTeacherMapper;
import com.university.response.ResponseData;
import com.university.service.StudentAndTeacherService;
import com.university.service.TeacherService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;


@Path("/studentandteacher")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentAndTeacherResource {

    private final StudentAndTeacherService studentAndTeacherService;
    private final StudentAndTeacherMapper studentAndTeacherMapper ;
    @Inject
    public StudentAndTeacherResource(StudentAndTeacherService studentAndTeacherService,StudentAndTeacherMapper studentAndTeacherMapper) {
        this.studentAndTeacherService = studentAndTeacherService;
        this.studentAndTeacherMapper = studentAndTeacherMapper;
    }

    //Read All
    @GET
    @Path("/getAll")
    public ResponseData<List<StudentAndTeacherDtoResponse>> getAll() {
        try {
            List<StudentAndTeacherDtoResponse> response = studentAndTeacherService.getAll();
          return new ResponseData<>(  response ,"Success",200);
        }catch (Exception e){
            return new ResponseData<>(null ,"Error: " + e.getMessage(),500);
        }
        }

//Read By id
    @GET
    @Path("/getById/{id}")
    public  ResponseData<StudentAndTeacherDtoResponse>getByIdStudentAndTeacher(@PathParam("id") Long id){
        try {
           Optional<StudentAndTeacherDtoResponse> existed = studentAndTeacherService.getByIdStudentAndTeacher(id);
            return existed.map(studentAndTeacherDtoResponse -> new ResponseData<>(studentAndTeacherDtoResponse, "Success", 200)).orElseGet(() -> new ResponseData<>(null, "Not found with id: " + id, 404));
        }catch (Exception e){
            return new ResponseData<>(null, "Error: " + e.getMessage(),500);
        }
    }


    //Create
    @POST
    @Path("/add")
    public ResponseData<StudentAndTeacherDtoResponse> add(StudentAndTeacherDtoRequest studentAndTeacher) {
        try {

            StudentAndTeacherDtoResponse created = studentAndTeacherService.add(studentAndTeacher);
            return new ResponseData<>(created, "Successfully added", 201);
        } catch (Exception e) {
            return new ResponseData<>(null, "Error: " +e.getMessage(),500);

        }
    }

    //Edit
    @PUT
    @Path("/edit/{id}")
    public ResponseData<StudentAndTeacherDtoResponse> edit(@PathParam("id")Long id, StudentAndTeacherDtoRequest studentAndTeacherParameter ){
try {

    Optional<StudentAndTeacherDtoResponse> updated = studentAndTeacherService.updateStudentAndTeacher(id,studentAndTeacherParameter);

    return updated.map(studentAndTeacherDtoResponse -> new ResponseData<>(studentAndTeacherDtoResponse, "Successfully updated", 201)).orElseGet(() -> new ResponseData<>(null, "Not found with id: " + id, 404));

}  catch(Exception e){
    return new ResponseData<>(null, "Error: "+e.getMessage(),500);
}



    }


    //Remove
    @DELETE
    @Path("delete/{id}")
    public ResponseData<StudentAndTeacherDtoResponse> delete(@PathParam("id") Long id) {
    try {
    studentAndTeacherService.deleteByIdStudentAndTeacher(id);
    return new ResponseData<>("Successfully deleted", 200);

    }

    catch (NotFoundException e){
        return new ResponseData<>(null,  e.getMessage(),404);
    }
    catch (Exception e){
        return new ResponseData<>(null, "Error" ,500);
    }

    }


}


