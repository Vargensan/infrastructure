package com.waf.infrastructure.core.exceptions.entity;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.Map;

/**
 * @author Bartlomiej Wos
 */
@Value
@Jacksonized
@Builder(builderClassName = "Builder", setterPrefix = "with")
public class Validated {

    String identifier;
    String message;
    Map<String, Object> additionalInformation;

}
