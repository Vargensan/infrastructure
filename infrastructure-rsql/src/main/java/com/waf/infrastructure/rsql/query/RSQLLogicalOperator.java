package com.waf.infrastructure.rsql.query;

/**
 * @author Bartlomiej Wos
 */
public enum RSQLLogicalOperator {

    AND_INNER(" and ") {
        @Override
        public String apply(RSQLCondition leftCondition, RSQLCondition rightCondition) {
            return "(" +leftCondition.get() + getLogicalOperator() + rightCondition.get()+")";
        }
    },
    AND_OUTER(" and ") {
        @Override
        public String apply(RSQLCondition leftCondition, RSQLCondition rightCondition) {
            return reduce("(" + leftCondition.get() + ")") + getLogicalOperator() + reduce("(" +rightCondition.get() + ")");
        }
    },
    OR_INNER(" or ") {
        @Override
        public String apply(RSQLCondition leftCondition, RSQLCondition rightCondition) {
            return "(" +leftCondition.get() + getLogicalOperator() + rightCondition.get()+")";
        }
    },
    OR_OUTER(" or ") {
        @Override
        public String apply(RSQLCondition leftCondition, RSQLCondition rightCondition) {
            return reduce("(" + leftCondition.get() + ")") + getLogicalOperator() + reduce("(" + rightCondition.get() + ")");
        }
    };

    private final String logicalOperator;

    RSQLLogicalOperator(String logicalOperator) {
        this.logicalOperator = logicalOperator;
    }

    public String getLogicalOperator() {
        return logicalOperator;
    }

    public abstract String apply(RSQLCondition leftCondition, RSQLCondition rightCondition);

    public static String reduce(String value) {
        while (value.startsWith("((") && value.endsWith("))")) {
            value = value.substring(1, value.length() - 1);
        }
        return value;
    }

}
