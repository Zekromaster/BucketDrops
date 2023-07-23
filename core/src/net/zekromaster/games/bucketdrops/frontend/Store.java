package net.zekromaster.games.bucketdrops.frontend;

import java.util.function.Function;

public interface Store<T> {

    T get(String name);
    T getOrCompute(String name, Function<String, T> generator);
    void dispose();
}
