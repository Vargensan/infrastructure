package com.waf.infrastructure.rsql.query;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Bartlomiej Wos
 */
public class RSQLCondition {

    private RSQLComparison mainComparison;

    private RSQLCondition leftCondition;
    private RSQLLogicalOperator logicalOperator;
    private RSQLCondition rightCondition;

    private RSQLCondition() {}

    public RSQLCondition(RSQLComparison comparison) {
        this.mainComparison = comparison;
    }

    public RSQLCondition(RSQLCondition condition) {
        this.mainComparison = condition.mainComparison;
        this.leftCondition = condition.leftCondition;
        this.logicalOperator = condition.logicalOperator;
        this.rightCondition = condition.rightCondition;
    }

    private RSQLCondition copy() {
        RSQLCondition rsqlCondition = new RSQLCondition();
        rsqlCondition.mainComparison = getMainComparison().map(RSQLComparison::deepCopy).orElse(null);
        rsqlCondition.leftCondition = getLeftCondition().map(RSQLCondition::copy).orElse(null);
        rsqlCondition.logicalOperator = this.logicalOperator;
        rsqlCondition.rightCondition = getRightCondition().map(RSQLCondition::copy).orElse(null);
        return rsqlCondition;
    }


    public static RSQLCondition and(RSQLCondition... condition) {
        return Arrays.stream(condition)
                .reduce((f,s) -> f.operation(s, RSQLLogicalOperator.AND_OUTER))
                .orElse(new RSQLCondition());
    }

    public RSQLCondition operation(RSQLCondition condition, RSQLLogicalOperator operator) {
        RSQLCondition newRoot = new RSQLCondition();

        newRoot.logicalOperator = operator;
        newRoot.rightCondition = new RSQLCondition(condition.copy());
        newRoot.leftCondition = this;

        return newRoot;
    }

    public RSQLCondition and(RSQLCondition condition) {
        RSQLCondition newRoot = new RSQLCondition();

        newRoot.logicalOperator = RSQLLogicalOperator.AND_INNER;
        newRoot.rightCondition = new RSQLCondition(condition.copy());
        newRoot.leftCondition = this;

        return newRoot;
    }

    public RSQLCondition or(RSQLCondition condition) {
        RSQLCondition newRoot = new RSQLCondition();

        newRoot.logicalOperator = RSQLLogicalOperator.OR_INNER;
        newRoot.rightCondition = new RSQLCondition(condition.copy());
        newRoot.leftCondition = this;

        return newRoot;
    }

    private Optional<RSQLComparison> getMainComparison() {
        return Optional.ofNullable(mainComparison);
    }

    private Optional<RSQLLogicalOperator> getLogicalOperator() {
        return Optional.ofNullable(logicalOperator);
    }

    private Optional<RSQLCondition> getLeftCondition() {
        return Optional.ofNullable(leftCondition);
    }

    private Optional<RSQLCondition> getRightCondition() {
        return Optional.ofNullable(rightCondition);
    }

    public String get() {
        if (getLogicalOperator().isPresent()) {
            return logicalOperator.apply(leftCondition, rightCondition);
        }
        else if (getRightCondition().isPresent()){
            return rightCondition.get();
        } else {
            return mainComparison.get();
        }
    }

}
