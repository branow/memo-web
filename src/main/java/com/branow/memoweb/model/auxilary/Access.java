package com.branow.memoweb.model.auxilary;


import com.branow.memoweb.exception.AccessTypeNotFoundException;

import java.util.Arrays;
import java.util.Optional;

public enum Access {

    PRIVATE("private"), PUBLIC("public");

    public static Access findByName(String name) {
        Optional<Access> res = Arrays.stream(Access.values())
                .filter(e -> e.toString().equals(name))
                .findAny();
        return res.orElseThrow(() -> new AccessTypeNotFoundException("Access not found: " + name));
    }

    private final String value;

    Access(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

}
