package com.waf.infrastructure.core.exceptions.control;

import com.waf.infrastructure.core.exceptions.entity.Violation;
import jakarta.enterprise.context.RequestScoped;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bartlomiej Wos
 */
@RequestScoped
public class ViolationRegistrationService {

    Set<Violation> violations = new HashSet<>();

    public void registerViolations(Collection<Violation> violations) {
        violations.forEach(this::registerViolation);
    }

    public void registerViolation(Violation violation) {
        violations.add(violation);
    }

    public Set<Violation> getViolations() {
        return violations;
    }

    public boolean hasViolations() {
        return !violations.isEmpty();
    }

}
