package com.waf.infrastructure.rsql.query;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Bartlomiej Wos
 */
public enum ValueStandardization {

    JOIN_COMMA_DELIMITER {
        @Override
        public String standardize(Collection<String> values) {
            return values.stream()
                    .map(this::standardize)
                    .collect(Collectors.joining(","));
        }

        @Override
        public String standardize(String value) {
            if (value.contains(" ")) {
                if (!value.startsWith("'") && !value.endsWith("'")) {
                    return '\'' + value + '\'';
                }
                if (!value.startsWith("'")) {
                    return '\'' + value;
                }
                if (!value.endsWith("'")) {
                    return value + '\'';
                }
            }
            return value;
        }

    },
    NONE {
        @Override
        public String standardize(Collection<String> values) {
            if (values.size() > 1) {
                throw new UnsupportedOperationException();
            } else {
                return values.stream()
                        .findAny()
                        .map(this::standardize)
                        .orElseThrow();
            }
        }

        @Override
        public String standardize(String value) {
            if (value.contains(" ")) {
                if (!value.startsWith("'") && !value.endsWith("'")) {
                    return '\'' + value + '\'';
                }
                if (!value.startsWith("'")) {
                    return '\'' + value;
                }
                if (!value.endsWith("'")) {
                    return value + '\'';
                }
            }
            return value;
        }
    };

    public abstract String standardize(Collection<String> values);

    public abstract String standardize(String value);

}
