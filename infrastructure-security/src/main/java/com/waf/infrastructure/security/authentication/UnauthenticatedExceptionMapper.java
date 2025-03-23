package com.waf.infrastructure.security.authentication;

import com.waf.infrastructure.core.exceptions.entity.ExceptionDTO;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnauthenticatedExceptionMapper implements ExceptionMapper<UnauthenticatedException> {

    @Override
    public Response toResponse(UnauthenticatedException exception) {
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .withMessage(exception.getMessage())
                .withReason(exception.getMessage())
                .withType(exception.getClass()
                        .getSimpleName())
                .build();
        return Response.status(Response.Status.FORBIDDEN)
                .entity(exceptionDTO)
                .build();
    }

}
