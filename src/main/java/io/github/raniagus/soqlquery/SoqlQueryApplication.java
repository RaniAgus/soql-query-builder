package io.github.raniagus.soqlquery;

import io.github.raniagus.soqlquery.common.SoqlQueryConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SoqlQueryConfiguration.class)
public class SoqlQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoqlQueryApplication.class, args);
    }
}
