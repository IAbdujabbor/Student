package com.university.resource;

import com.university.dto.request.DisciplineDtoRequest;
import com.university.dto.response.DisciplineDtoResponse;
import com.university.entity.Discipline;
import com.university.mapper.DisciplineMapper;
import com.university.response.ResponseData;
import com.university.service.DisciplineService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;


@Path("discipline/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class DisciplineResource {

    private final DisciplineService disciplineService;


    @Inject
    public DisciplineResource(DisciplineService disciplineService ) {
        this.disciplineService = disciplineService;

    }

    //Read All
    @Path("/getAll")
    @GET
    public ResponseData<List<DisciplineDtoResponse>> getAll() {
        try {
            List<DisciplineDtoResponse> disciplines = disciplineService.getAll();
            return new ResponseData<>(disciplines, "Success", 200);
        } catch (Exception e) {
            return new ResponseData<>(null, "Error : " + e.getMessage(), 500);
        }


    }

    //Read By id
    @Path("/getById/{id}")
    @GET
    public ResponseData<DisciplineDtoResponse> getDisciplineById(@PathParam("id") Long id) {
        try {


            return disciplineService.getByIdDiscipline(id)
                    .map(discipline -> new ResponseData<>(discipline, "Success", 200))
                    .orElse(new ResponseData<>(null, "Discipline not found with id: " + id, 404));

        } catch (Exception e) {
            return new ResponseData<>(null, "Error : " + e.getMessage(), 500);
        }
    }

    //Create
    @POST
    @Path("/add")
    @Transactional
    public ResponseData<DisciplineDtoResponse> add(DisciplineDtoRequest disciplineParameter) {
        try {

            DisciplineDtoResponse discipline = disciplineService.add(disciplineParameter);
            return new ResponseData<>(discipline, "Success", 201);
        } catch (Exception e) {
            return new ResponseData<>(null, "Error : " + e.getMessage(), 500);
        }
    }

    //Edit
    @PUT
    @Path("/edit/{id}")
    @Transactional
    public ResponseData<DisciplineDtoResponse> edit(@PathParam("id") Long id, DisciplineDtoRequest disciplineRequest) {
        try {
            DisciplineDtoResponse updated = disciplineService.updateDiscipline(id, disciplineRequest);
            return new ResponseData<>(updated, "Discipline updated successfully", 200);
        } catch (Exception e) {
            return new ResponseData<>(null, "Error: " + e.getMessage(), 500);
        }
    }

    //Delete

    @DELETE
    @Path("/delete/{id}")
    @Transactional
    public ResponseData<Void> deleteByIdDiscipline(@PathParam("id") Long id) {
        try {
            Optional<DisciplineDtoResponse> deleted = disciplineService.deleteByIdDiscipline(id);
            if (deleted.isEmpty()) {
                return new ResponseData<>(null, "Discipline not found with id: " + id, 404);
            }
            return new ResponseData<>(null, "Discipline deleted successfully", 200);
        } catch (Exception e) {
            return new ResponseData<>(null, "Error deleting discipline: " + e.getMessage(), 500);
        }
    }


}

