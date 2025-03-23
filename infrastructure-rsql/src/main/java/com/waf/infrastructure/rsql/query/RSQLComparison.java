package com.waf.infrastructure.rsql.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * @author Bartlomiej Wos
 */
public class RSQLComparison {

    private final String selector;
    private final boolean isNegated;
    private final RSQLComparisonOperator rsqlComparisonOperator;
    private final List<String> arguments;

    private RSQLComparison(Builder builder) {
        this.selector = Objects.requireNonNull(builder.selector);
        this.isNegated = builder.isNegated;
        this.rsqlComparisonOperator = Objects.requireNonNull(builder.rsqlComparisonOperator);
        this.arguments = Objects.requireNonNull(builder.arguments);
    }

    private RSQLComparison(String selector,
                           boolean isNegated,
                           RSQLComparisonOperator rsqlComparisonOperator,
                           List<String> arguments) {
        this.selector = selector;
        this.isNegated = isNegated;
        this.rsqlComparisonOperator = rsqlComparisonOperator;
        this.arguments = arguments;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String get() {
        return selector + rsqlComparisonOperator.apply(isNegated, arguments);
    }

    public RSQLComparison deepCopy() {
        return new RSQLComparison(selector, isNegated, rsqlComparisonOperator, List.copyOf(arguments));
    }

    public static class Builder {

        private String selector;
        private boolean isNegated;
        private RSQLComparisonOperator rsqlComparisonOperator;
        private final List<String> arguments = new ArrayList<>();

        private Builder() {}

        public Builder withSelector(String selector) {
            this.selector = selector;
            return this;
        }

        public Builder withOperator(RSQLComparisonOperator rsqlComparisonOperator) {
            this.rsqlComparisonOperator = rsqlComparisonOperator;
            return this;
        }

        public Builder negate() {
            this.isNegated = !isNegated;
            return this;
        }

        public Builder withArgument(String argument) {
            this.arguments.add(argument);
            return this;
        }

        public Builder withArguments(Collection<String> arguments) {
            this.arguments.addAll(arguments);
            return this;
        }

        public RSQLComparison build() {
            return new RSQLComparison(this);
        }

    }

}
