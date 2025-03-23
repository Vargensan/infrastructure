package com.waf.infrastructure.security.authorization;

public class AuthenticationHeaderParser {

    private AuthenticationHeaderParser() {}

    public static String parseToSend(String authorizationHeader) {
        return "Bearer " + authorizationHeader;
    }

    public static String parseToRead(String authorizationHeader) {
        return authorizationHeader.replace("Bearer ", "");
    }

}
