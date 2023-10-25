package com.branow.memoweb.model.auxilary;

import com.branow.memoweb.exception.entitynotfound.StudyTypeNotFoundException;

import java.util.Arrays;
import java.util.Optional;

public enum StudyTypeName {

    ORALLY("orally"), WRITING("writing");

    public static StudyTypeName findByName(String name) {
        Optional<StudyTypeName> res = Arrays.stream(StudyTypeName.values())
                .filter(e -> e.toString().equals(name))
                .findAny();
        return res.orElseThrow(() -> new StudyTypeNotFoundException("name", name));
    }

    private final String value;

    StudyTypeName(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
