package com.waf.infrastructure.security.filter;

import com.waf.infrastructure.security.authorization.AuthenticationHeaderParser;
import com.waf.infrastructure.security.context.AuthenticationToken;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.client.ClientRequestContext;
import jakarta.ws.rs.client.ClientRequestFilter;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@ApplicationScoped
@RequiredArgsConstructor
public class AuthenticationTokenFilterAppender implements ClientRequestFilter {

    private final AuthenticationToken authenticationToken;

    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        clientRequestContext.getHeaders()
                .add("Authorization", AuthenticationHeaderParser.parseToSend(authenticationToken.getToken()));
    }

}
