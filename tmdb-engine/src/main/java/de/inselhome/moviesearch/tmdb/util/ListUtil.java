package de.inselhome.moviesearch.tmdb.util;

import java.util.List;

public final class ListUtil {

    private ListUtil() { }

    public static <E> List<E> add(final List<E> source, final List<E> target, final int maxTargetSize) {
        int maxNewItems = maxTargetSize - target.size() < source.size() ? maxTargetSize - target.size() : source.size();

        for (int i = 0; i < maxNewItems; i++) {
            target.add(source.get(i));
        }

        return target;
    }
}
