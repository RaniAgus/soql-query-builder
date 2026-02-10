package io.github.raniagus.featureoverride.configuration;

import java.util.List;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.raniagus.featureoverride.model.ConditionExpression;
import io.github.raniagus.featureoverride.model.Context;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import org.springframework.core.io.Resource;

@Data
@Component
public class FeatureConfiguration {
    @Value("classpath:examples/features-config.json")
    private Resource featuresConfig;
    private Features features;

    record Features(Map<String, Boolean> defaults, List<FeatureOverride> overrides) {}

    private ObjectMapper objectMapper;

    public FeatureConfiguration(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() throws IOException {
        features = objectMapper.readerFor(Features.class).readValue(featuresConfig.getContentAsByteArray());
    }

    public record FeatureOverride(
            Map<String, Boolean> features,
            ConditionExpression condition
    ) {}

    public Map<String, Boolean> getFeatures(Map<String, String> params) {
        Context context = new Context(params);
        Map<String, Boolean> features = new HashMap<>(this.features.defaults());
        for (FeatureOverride override : this.features.overrides()) {
            if (override.condition.test(context)) {
                features.putAll(override.features);
            }
        }
        return features;
    }
}
