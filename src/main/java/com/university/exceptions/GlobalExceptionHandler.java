package com.university.exceptions;
import com.university.response.ResponseData;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception){

        if(exception instanceof NotFoundException){
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(new ResponseData<>(exception.getMessage(),404))
                    .build();
        }
        if(exception instanceof InternalServerException){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseData<>(exception.getMessage(),500))
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ResponseData<>("Unexpected error" + exception.getMessage(),500))
                .build();
    }
}
