package io.github.raniagus.soqlquery.common;

import lombok.Getter;

import java.util.Map;
import java.util.Objects;

@Getter
public class InvalidPathParamException extends RuntimeException {
    private final String name;

    public InvalidPathParamException(String name, String message) {
        super(Objects.requireNonNull(message, "message must not be null"));
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    public Map<String, String> asMap() {
        return Map.of(getName(), getMessage());
    }
}
