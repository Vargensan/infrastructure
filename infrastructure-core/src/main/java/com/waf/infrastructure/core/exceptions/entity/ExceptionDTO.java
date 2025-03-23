package com.waf.infrastructure.core.exceptions.entity;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.Set;

/**
 * @author Bartlomiej Wos
 */
@Value
@Jacksonized
@Builder(builderClassName = "Builder", setterPrefix = "with")
public class ExceptionDTO {

    String type;
    String reason;
    String message;
    List<String> stackTrace;
    @Singular
    Set<Violation> violations;

}
