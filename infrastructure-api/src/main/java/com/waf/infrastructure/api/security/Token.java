package com.waf.infrastructure.api.security;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(setterPrefix = "with")
public class Token {

    String accessToken;
    String refreshToken;

}
