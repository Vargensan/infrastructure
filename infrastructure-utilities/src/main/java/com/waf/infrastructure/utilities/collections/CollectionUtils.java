package com.waf.infrastructure.utilities.collections;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Bartlomiej Wos
 */
@SuppressWarnings("unused")
public class CollectionUtils {

    private CollectionUtils() {}

    public static <T> Set<T> joinAsSet(Collection<T> first, Collection<T> second) {
        return Stream.of(first, second)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public static <T> List<T> joinAsList(Collection<T> first, Collection<T> second) {
        return Stream.of(first, second)
                .flatMap(Collection::stream)
                .toList();
    }

    public static <T> T requireSingle(Collection<T> objects) {
        if (objects.size() > 1) {
            throw new IllegalArgumentException("Expected only one object in collection.");
        }
        return getAny(objects);
    }

    public static <T> T getAny(Collection<T> objects) {
        return objects.stream()
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Collection does not contain any element."));
    }

}
