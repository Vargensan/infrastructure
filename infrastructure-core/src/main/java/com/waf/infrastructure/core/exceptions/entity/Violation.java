package com.waf.infrastructure.core.exceptions.entity;

import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

/**
 * @author Bartlomiej Wos
 */
@Value
@Jacksonized
@Builder(builderClassName = "Builder", setterPrefix = "with")
public class Violation {

    @NonNull
    String type;
    String message;
    Validated validated;
    @Singular("violationRelatedObject")
    List<ViolationRelated> violationRelatedObjects;


}
