package com.company.supplier;

@FunctionalInterface
public interface Supplier<T> {
    T get();
}
