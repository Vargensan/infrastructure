package com.waf.infrastructure.security.filter;

import com.waf.infrastructure.security.authorization.AuthenticationHeaderParser;
import com.waf.infrastructure.security.context.AuthenticationToken;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@WebFilter("/rest/*")
@RequiredArgsConstructor
public class InvocationContextFilter implements Filter {

    private final AuthenticationToken authenticationToken;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Map<String, ArrayList<String>> headers = Collections.list(httpServletRequest.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(Function.identity(), h -> Collections.list(httpServletRequest.getHeaders(h))));
        String authorizationHeader = headers.get("Authorization").stream()
                .findAny()
                .orElse("");

        authenticationToken.setToken(AuthenticationHeaderParser.parseToRead(authorizationHeader));
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            authenticationToken.setToken(null);
        }
    }

}
