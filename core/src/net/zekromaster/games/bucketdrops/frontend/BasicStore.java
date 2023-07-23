package net.zekromaster.games.bucketdrops.frontend;

import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

public abstract class BasicStore<T> {

    private Map<String, T> map;

    @Inject
    protected BasicStore() {
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
