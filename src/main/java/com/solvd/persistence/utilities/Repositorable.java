package com.solvd.persistence.utilities;

import java.util.Optional;

public interface Repositorable<T> {
    void create(T t);

    Optional<T> findById(Long id);
}
