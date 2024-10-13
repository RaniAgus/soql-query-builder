package io.github.raniagus.soqlquery.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Data
@ConfigurationProperties(prefix = "soql")
public class SoqlQueryConfiguration {
    private Map<String, List<String>> objects;
}
