package io.github.raniagus.soqlquery.query.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.stream.Collectors;

public record MultiExpression(
        Operator operator,
        @NotNull @Size(min = 2) @Valid List<ConditionExpression> conditions
) implements ConditionExpression {
    @Override
    public String toExpression() {
        return conditions.stream()
                .map(ConditionExpression::toExpression)
                .collect(Collectors.joining(" " + operator.getSymbol() + " ", "(", ")"));
    }
}
