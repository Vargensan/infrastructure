package com.waf.infrastructure.rsql.query;

import java.util.Collection;

/**
 * @author Bartlomiej Wos
 */
public enum RSQLComparisonOperator {

    IN("=in=", ValueStandardization.JOIN_COMMA_DELIMITER) {
        @Override
        public String apply(boolean isNegated, Collection<String> values) {
            if (isNegated) {
                return RSQLComparisonOperator.OUT.apply(false, values);
            }
            String operationSuffix = "({1})";
            String replace = operationSuffix.replace("{1}", standardize(values));
            return this.getOperator() + replace;
        }
    },
    OUT("=out=", ValueStandardization.JOIN_COMMA_DELIMITER) {
        @Override
        public String apply(boolean isNegated, Collection<String> values) {
            if (isNegated) {
                return RSQLComparisonOperator.IN.apply(false, values);
            }
            String operationSuffix = "({1})";
            String replace = operationSuffix.replace("{1}", standardize(values));
            return this.getOperator() + replace;
        }
    },
    EQUALS("==", ValueStandardization.NONE) {
        @Override
        public String apply(boolean isNegated, Collection<String> values) {
            if (isNegated) {
                return RSQLComparisonOperator.NOT_EQUALS.apply(false, values);
            }
            String operationSuffix = "{1}";
            String replace = operationSuffix.replace("{1}", standardize(values));
            return this.getOperator() + replace;
        }
    },
    NOT_EQUALS("!=", ValueStandardization.NONE) {

        @Override
        public String apply(boolean isNegated, Collection<String> values) {
            if (isNegated) {
                return RSQLComparisonOperator.EQUALS.apply(false, values);
            }
            String operationSuffix = "{1}";
            String replace = operationSuffix.replace("{1}", standardize(values));
            return this.getOperator() + replace;
        }
    };

    private final String operator;
    private final ValueStandardization standardization;

    RSQLComparisonOperator(String operator, ValueStandardization standardization) {
        this.operator = operator;
        this.standardization = standardization;
    }

    public abstract String apply(boolean isNegated, Collection<String> values);

    public String getOperator() {
        return operator;
    }

    public String standardize(Collection<String> values) {
        return standardization.standardize(values);
    }

}
