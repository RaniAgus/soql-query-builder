package io.github.raniagus.featureoverride.service;

import com.fasterxml.jackson.databind.ObjectReader;
import io.github.raniagus.featureoverride.configuration.FeaturesConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.raniagus.featureoverride.model.Context;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Data
@Service
public class FeatureService {
    @Value("classpath:features/config.json")
    private Resource featuresFile;
    private ObjectReader featuresReader;
    private FeaturesConfiguration features;

    public FeatureService(ObjectMapper objectMapper) {
        this.featuresReader = objectMapper.readerFor(FeaturesConfiguration.class);
    }

    @PostConstruct
    public void init() throws IOException {
        features = featuresReader.readValue(featuresFile.getContentAsByteArray());
    }

    public Map<String, Boolean> getFeatures(Map<String, String> params) {
        Context context = new Context(params);
        Map<String, Boolean> features = new HashMap<>(this.features.defaults());
        for (var override : this.features.overrides()) {
            if (override.condition().test(context)) {
                features.putAll(override.features());
            }
        }
        return features;
    }
}
