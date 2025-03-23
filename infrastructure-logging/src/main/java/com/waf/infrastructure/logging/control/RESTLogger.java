package com.waf.infrastructure.logging.control;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;

@Provider
@Slf4j
@SuppressWarnings("unused")
public class RESTLogger implements ContainerRequestFilter, ContainerResponseFilter {

    @Context
    private UriInfo uriInfo;

    @ConfigProperty(name = "http.log.requests", defaultValue = "false")
    boolean logRequestEnabled;

    @ConfigProperty(name = "http.log.response", defaultValue = "false")
    boolean logResponseEnabled;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (logRequestEnabled) {
            log.info("Request: {}", containerRequestContext);
        }
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext,
                       ContainerResponseContext containerResponseContext) throws IOException {
        if (logResponseEnabled) {
            log.info("Response: {}", containerResponseContext);
        }
    }

}
