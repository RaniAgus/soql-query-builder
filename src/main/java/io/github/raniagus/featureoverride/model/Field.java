package io.github.raniagus.featureoverride.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.github.zafarkhaja.semver.Version;
import io.github.raniagus.featureoverride.constant.ContextParams;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "field",
        visible = true,
        defaultImpl = Field.Text.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(name = ContextParams.PLATFORM, value = Field.Text.class),
        @JsonSubTypes.Type(name = ContextParams.VERSION, value = Field.SemVer.class),
        @JsonSubTypes.Type(name = ContextParams.DEVICE, value = Field.Text.class),
        @JsonSubTypes.Type(name = ContextParams.OS_VERSION, value = Field.Number.class),
})
public sealed interface Field {
    record Text(String name, String value) implements Field {}

    record SemVer(String name, Version value) implements Field {}

    record Number(String name, Integer value) implements Field {}
}
