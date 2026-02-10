package io.github.raniagus.featureoverride.configuration;

import io.github.raniagus.featureoverride.model.ConditionExpression;
import java.util.List;
import java.util.Map;

public record FeaturesConfiguration(
        Map<String, Boolean> defaults,
        List<FeatureOverride> overrides
) {
    public record FeatureOverride(
            Map<String, Boolean> features,
            ConditionExpression condition
    ) {}
}
