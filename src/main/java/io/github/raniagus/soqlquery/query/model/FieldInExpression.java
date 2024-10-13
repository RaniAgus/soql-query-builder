package io.github.raniagus.soqlquery.query.model;

import io.github.raniagus.soqlquery.query.util.QueryUtils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class FieldInExpression implements ConditionExpression {
    Operator operator;

    @NotNull
    @Pattern(regexp = "^\\w+$")
    String field;

    @NotNull @Size(min = 2)
    List<Object> values;

    @Override
    public String getExpression() {
        return values.stream()
                .map(QueryUtils::formatValue)
                .collect(Collectors.joining(",", "(" + field + " " + operator.getSymbol() + " (", "))"));
    }
}
