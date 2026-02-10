package io.github.raniagus.featureoverride.model;

import com.github.zafarkhaja.semver.ParseException;
import com.github.zafarkhaja.semver.Version;
import jakarta.annotation.Nullable;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Context {
    private final Map<String, String> params;
    private final Map<String, Version> versionParams;
    private final Map<String, Integer> numberParams;

    public Context(Map<String, String> params) {
        this.params = params;
        this.versionParams = new ConcurrentHashMap<>();
        this.numberParams = new ConcurrentHashMap<>();
    }

    public boolean contains(String name, String expected) {
        return Objects.equals(getString(name), expected);
    }

    public boolean isEqualTo(Field field) {
        try {
            return switch (field) {
                case Field.Text(String name, String value) -> Objects.equals(getString(name), value);
                case Field.SemVer(String name, Version value) -> ObjectUtils.compare(getVersion(name), value) == 0;
                case Field.Number(String name, Integer value) -> ObjectUtils.compare(getInteger(name), value) == 0;
            };
        } catch (IllegalArgumentException | ParseException e) {
            return false;
        }
    }

    public int compareTo(Field field) {
        try {
            return switch (field) {
                case Field.Text(String name, String value) -> ObjectUtils.compare(getString(name), value);
                case Field.SemVer(String name, Version value) -> ObjectUtils.compare(getVersion(name), value);
                case Field.Number(String name, Integer value) -> ObjectUtils.compare(getInteger(name), value);
            };
        } catch (IllegalArgumentException | ParseException e) {
            return 0;
        }
    }

    private @Nullable String getString(String name) {
        return params.get(name);
    }

    private @Nullable Version getVersion(String name) {
        String value = params.get(name);
        if (value == null) {
            return null;
        }
        return versionParams.computeIfAbsent(name, key -> Version.valueOf(value));
    }

    private @Nullable Integer getInteger(String name) {
        String value = params.get(name);
        if (value == null) {
            return null;
        }
        return numberParams.computeIfAbsent(name, key -> Integer.valueOf(value));
    }
}
