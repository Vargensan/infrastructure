package com.waf.infrastructure.core.exceptions.entity;

import java.util.Map;

/**
 * @author Bartlomiej Wos
 */
public class ViolationRelated {

    String identifier;
    String message;
    Map<String, Object> additionalInformations;

}
