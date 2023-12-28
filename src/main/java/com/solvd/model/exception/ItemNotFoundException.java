package com.solvd.model.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String item) {
        super(item + " not found");
    }
}
