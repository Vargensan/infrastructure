package com.waf.infrastructure.security;

import com.waf.infrastructure.security.authentication.AuthenticationProperties;
import com.waf.infrastructure.security.authentication.UnauthenticatedException;
import com.waf.infrastructure.security.authorization.AuthenticationHeaderParser;
import com.waf.infrastructure.security.authorization.PublicAccess;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Provider
@Slf4j
@SuppressWarnings("unused")
@Priority(Priorities.AUTHENTICATION)
public class AuthorizationController implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;
    private AuthenticationProperties authenticationProperties;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        PublicAccess annotation = resourceInfo.getResourceMethod().getAnnotation(PublicAccess.class);
        try {
            if (annotation == null) {
                identify(containerRequestContext);
            }
        } catch (Exception e) {
            throw new UnauthenticatedException("Unauthenticated.");
        }
    }

    public void identify(ContainerRequestContext containerRequestContext) {
        String token = AuthenticationHeaderParser.parseToRead(containerRequestContext.getHeaderString("Authorization"));
        PublicKey publicKey = getPublicKey();
        Jws<Claims> claimsJws = Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token);
    }

    @SneakyThrows
    private PublicKey getPublicKey() {
        String publicKey = authenticationProperties.getPublicKey()
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey));
        return KeyFactory.getInstance("RSA")
                .generatePublic(keySpec);
    }


}
