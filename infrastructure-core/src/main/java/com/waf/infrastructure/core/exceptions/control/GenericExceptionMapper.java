package com.waf.infrastructure.core.exceptions.control;

import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import com.waf.infrastructure.core.exceptions.entity.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Bartlomiej Wos
 */
@SuppressWarnings("ununsed")
@Provider
@Slf4j
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    private final ViolationRegistrationService violationRegistrationService;

    @Inject
    public GenericExceptionMapper(ViolationRegistrationService violationRegistrationService) {
        this.violationRegistrationService = violationRegistrationService;
    }

    @Override
    public Response toResponse(Throwable exception) {
        log.error("An exception occurred.", exception);
        if (exception instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .build();
        }
        ExceptionDTO.Builder builder = ExceptionDTO.builder();
        Optional.ofNullable(exception.getCause())
                .map(Throwable::toString)
                .ifPresent(builder::withReason);
        Optional.of(exception.getClass())
                .map(Class::getSimpleName)
                .ifPresent(builder::withType);

        ExceptionDTO build = builder.withMessage(exception.getMessage())
                .withStackTrace(Arrays.stream(exception.getStackTrace()).map(StackTraceElement::toString).toList())
                .withViolations(violationRegistrationService.getViolations())
                .build();
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(build)
                .build();
    }

}
