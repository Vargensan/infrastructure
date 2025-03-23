package com.waf.infrastructure.core.exceptions.control;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import com.waf.infrastructure.core.exceptions.entity.ExternalServiceException;

/**
 * @author Bartlomiej Wos
 */
@Provider
public class ExternalServiceExceptionHandler implements ExceptionMapper<ExternalServiceException> {

    @Override
    public Response toResponse(ExternalServiceException exception) {
        return null;
    }

}
