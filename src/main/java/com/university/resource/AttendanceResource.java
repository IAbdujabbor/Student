package com.university.resource;


import  com.university.entity.*;
import  com.university.service.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


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

    @GET
    @Path("/getAll")
    public List<Attendance> getAll() {return attendanceService.getAll();}

    @POST
    @Path("/add")
    @Transactional
        public Response addAttendance(Attendance attendance) {

         attendanceService.addAttendance( attendance);
        return Response
                .status(Response.Status.CREATED)
                .entity(attendance)
                .build();

    }





}
