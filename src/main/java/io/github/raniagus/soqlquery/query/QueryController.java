package io.github.raniagus.soqlquery.query;

import io.github.raniagus.soqlquery.common.InvalidPathParamException;
import io.github.raniagus.soqlquery.common.SoqlQueryConfiguration;
import io.github.raniagus.soqlquery.query.dto.QueryObjectsRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class QueryController {
    private final SoqlQueryConfiguration configuration;

    @PostMapping("/objects/{objectType}")
    public String queryObjectsBy(@PathVariable("objectType") String objectType,
                                 @Valid @RequestBody QueryObjectsRequest request) {
        if (!configuration.getObjects().containsKey(objectType)) {
            throw new InvalidPathParamException("objectType", "Unknown value: " + objectType);
        }

        return "SELECT %s FROM %s WHERE %s".formatted(
                String.join(",", configuration.getObjects().get(objectType)),
                objectType,
                request.where().toSOQL()
        );
    }
}
