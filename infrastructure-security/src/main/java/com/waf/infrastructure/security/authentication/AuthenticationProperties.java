package com.waf.infrastructure.security.authentication;

import io.quarkus.runtime.annotations.StaticInitSafe;
import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@StaticInitSafe
@ConfigMapping(prefix = "infrastructure.jwt")
public interface AuthenticationProperties {

    @WithName("secret-key")
    String getSecretKey();

    @WithName("public-key")
    String getPublicKey();

}
