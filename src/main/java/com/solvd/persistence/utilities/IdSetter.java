package com.solvd.persistence.utilities;

@FunctionalInterface
public interface IdSetter<T> {
    void setId(T object, Long id);
}
