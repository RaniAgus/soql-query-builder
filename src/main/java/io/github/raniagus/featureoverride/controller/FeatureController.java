package io.github.raniagus.featureoverride.controller;

import io.github.raniagus.featureoverride.configuration.FeatureConfiguration;
import jakarta.validation.Valid;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FeatureController {
    private final FeatureConfiguration configuration;

    @PostMapping("/features")
    public ResponseEntity<Object> getFeatures(@Valid @RequestBody Map<String, String> context) {
        return ResponseEntity.ok(configuration.getFeatures(context));
    }
}
