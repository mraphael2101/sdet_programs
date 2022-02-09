package com.company.random.supplier;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
