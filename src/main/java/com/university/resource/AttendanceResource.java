package com.university.resource;


import com.university.dto.response.AttendanceDtoResponse;
import com.university.dto.request.AttendanceDtoRequest;
import com.university.dto.request.AttendanceDtoRequest;
import com.university.entity.*;
import com.university.response.ResponseData;
import com.university.service.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;


@Path("/attendance")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@ApplicationScoped
public class AttendanceResource {

    private final AttendanceService attendanceService;

    @Inject
    public AttendanceResource(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    //Read All
    @GET
    @Path("/getAll")
    public ResponseData<List<AttendanceDtoResponse>> getAll() {
        try {
            var list = attendanceService.getAll();
            return new ResponseData<>(list, "Successfully Get All", 200);
        } catch (Exception e) {

            return new ResponseData<>(e.getMessage(), 500);
        }
    }

    //Read By id
    @GET
    @Path("/getById/{id}")

    public ResponseData<AttendanceDtoResponse> getAttendance(@PathParam("id") Long id) {
        try {
            var attendance = attendanceService.getByIdAttendance(id)
                    .orElseThrow(() -> new NotFoundException("Attendance not found with id: " + id));
            return new ResponseData<>(attendance, "Attendance retrieved successfully", 200);
        } catch (NotFoundException e) {
            return new ResponseData<>(e.getMessage(), 404);
        } catch (Exception e) {
            return new ResponseData<>("Error fetching attendance", 500);
        }
    }


    //Create
    @POST
    @Path("/add")
    @Transactional
    public ResponseData<AttendanceDtoResponse> addAttendance(AttendanceDtoRequest attendance) {
        try {
            var created = attendanceService.addAttendance(attendance);
            return new ResponseData<>(created, "Attendance successfully added", 201);
        } catch (Exception e) {
            return new ResponseData<>("Error creating attendance: " + e.getMessage(), 500);

        }
    }

    //Edit
    @PUT
    @Path("/edit/{id}")
    @Transactional
    public ResponseData<AttendanceDtoResponse> editAttendance(@PathParam("id") Long id, AttendanceDtoRequest attendance) {

        try {
            AttendanceDtoResponse updated = attendanceService.updateAttendance(id, attendance)
                    .orElseThrow(() -> new NotFoundException("Attendance not found with id: " + id));
            return new ResponseData<>(updated, "Attendance updated successfully", 200);
        } catch (NotFoundException e) {
            return new ResponseData<>(e.getMessage(), 404);

        } catch (Exception e) {
            return new ResponseData<>("Error updating attendance: " + e.getMessage(), 500);
        }
    }

    //Remove
    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public ResponseData<Void> deleteAttendance(@PathParam("id") Long id) {
        try {
            Optional<AttendanceDtoResponse> removed = attendanceService.deleteById(id);
            if (removed.isEmpty()) {
                throw new NotFoundException("Attendance not found with id: " + id);
            }
            return new ResponseData<>(null, "Attendance deleted successfully", 200);
        } catch (NotFoundException e) {
            return new ResponseData<>(e.getMessage(), 404);
        } catch (Exception e) {
            return new ResponseData<>("Error deleting attendance", 500);
        }

    }


}
