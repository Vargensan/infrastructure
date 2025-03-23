package com.waf.infrastructure.utilities.collections;

import java.util.Collection;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Bartlomiej Wos
 */
@SuppressWarnings("unused")
public class SetUtils {

    private SetUtils() {}

    public static <T> Set<T> difference(Set<T> basic, Collection<T> other) {
        return other.stream()
                .filter(contains(basic).negate())
                .collect(Collectors.toSet());

    }

    private static <T> Predicate<T> contains(Set<T> basic) {
        return basic::contains;
    }

}
