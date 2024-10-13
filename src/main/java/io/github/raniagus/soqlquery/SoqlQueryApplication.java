package io.github.raniagus.soqlquery;

import io.github.raniagus.soqlquery.common.SoqlQueryConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(SoqlQueryConfiguration.class)
@Slf4j
public class SoqlQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoqlQueryApplication.class, args);
    }
}
