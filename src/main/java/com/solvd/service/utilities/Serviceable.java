package com.solvd.service.utilities;


public interface Serviceable<T> {
    T create(T t);

    T loadById(Long id);
}
