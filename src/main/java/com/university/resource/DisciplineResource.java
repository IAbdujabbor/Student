package com.university.resource;

import com.university.entity.Discipline;
import com.university.service.DisciplineService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;

import java.util.List;


@Path("discipline/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DisciplineResource {

    private final DisciplineService disciplineService;

    @Inject
    public DisciplineResource(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @Path("/getAll")
    @GET
    public List<Discipline> getAll() {
        return disciplineService.getAll();
    }

    @Transactional
    @Path("/add")
    @POST
    public Response add(Discipline discipline) {
        disciplineService.add(discipline);
        return Response.status(Response.Status.CREATED)
                .entity(discipline)
                .build();

    }

    @Transactional
    @DELETE
    @Path("/delete/{id}")
    public Response deleteByIdDiscipline(@PathParam("id") Long id) {
        disciplineService.deleteByIdDiscipline(id);
        return Response
                .status(Response.Status.OK)
                .build();
    }




}

