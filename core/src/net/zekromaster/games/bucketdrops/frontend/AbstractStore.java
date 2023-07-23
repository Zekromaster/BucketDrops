package net.zekromaster.games.bucketdrops.frontend;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

abstract class AbstractStore<T> implements Store<T> {

    private final Map<String, T> map;

    @Inject
    protected AbstractStore() {
        this.map = new LinkedHashMap<>();
    }

    protected abstract T defaultGenerator(String name);
    protected abstract void disposeOfOne(T object);

    public T get(String name) {
        return this.getOrCompute(
            name,
            this::defaultGenerator
        );
    }

    public T getOrCompute(String name, Function<String, T> generator) {
        return map.computeIfAbsent(
            name,
            generator
        );
    }

    public void dispose() {
        map.values().forEach(this::disposeOfOne);
    }

}
