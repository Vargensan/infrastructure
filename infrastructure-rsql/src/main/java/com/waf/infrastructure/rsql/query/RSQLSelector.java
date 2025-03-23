package com.waf.infrastructure.rsql.query;

import java.util.Collection;
import java.util.List;

/**
 * @author Bartlomiej Wos
 */
public class RSQLSelector {

    private RSQLSelector() {}

    public static RSQLOperator of(String attribute) {
        RSQLComparison.Builder expressionBuilder = RSQLComparison.builder()
                .withSelector(attribute);
        return new RSQLOperator(expressionBuilder);
    }

    public static class RSQLOperator {

        private final RSQLComparison.Builder expressionBuilder;

        private RSQLOperator(RSQLComparison.Builder expressionBuilder) {
            this.expressionBuilder = expressionBuilder;
        }

        public RSQLCondition ne(String values) {
            expressionBuilder.withOperator(RSQLComparisonOperator.NOT_EQUALS);
            expressionBuilder.withArgument(values);
            return new RSQLCondition(expressionBuilder.build());
        }

        public RSQLCondition in(String... values) {
            return in(List.of(values));
        }

        public RSQLCondition in(Collection<String> values) {
            expressionBuilder.withOperator(RSQLComparisonOperator.IN);
            expressionBuilder.withArguments(values);
            return new RSQLCondition(expressionBuilder.build());
        }

        public RSQLCondition out(String... values) {
            return out(List.of(values));
        }

        public RSQLCondition out(Collection<String> values) {
            expressionBuilder.withOperator(RSQLComparisonOperator.OUT);
            expressionBuilder.withArguments(values);
            return new RSQLCondition(expressionBuilder.build());
        }

        public RSQLCondition eq(String value) {
            expressionBuilder.withOperator(RSQLComparisonOperator.EQUALS);
            expressionBuilder.withArgument(value);
            return new RSQLCondition(expressionBuilder.build());
        }

        public RSQLOperator not() {
            expressionBuilder.negate();
            return new RSQLOperator(expressionBuilder);
        }

    }

}
