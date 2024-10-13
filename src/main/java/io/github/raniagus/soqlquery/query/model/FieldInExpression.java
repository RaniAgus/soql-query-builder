package io.github.raniagus.soqlquery.query.model;

import io.github.raniagus.soqlquery.query.util.QueryUtils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.stream.Collectors;

public record FieldInExpression(
        Operator operator,
        @NotNull @Pattern(regexp = "^\\w+$") String field,
        @NotNull @Size(min = 2) List<Object> values
) implements ConditionExpression {
    @Override
    public String toExpression() {
        return values.stream()
                .map(QueryUtils::formatValue)
                .collect(Collectors.joining(",", "(" + field + " " + operator.getSymbol() + " (", "))"));
    }
}
