package com.waf.infrastructure.api.events;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class DomainEvent<T> {

    DomainEventType domainEventType;
    T eventBody;

}
