package io.github.raniagus.soqlquery.query.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.stream.Collectors;

record MultiExpression(
        Operator operator,
        @NotNull @Size(min = 2) @Valid List<ConditionExpression> conditions
) implements ConditionExpression {
    @Override
    public String toSOQL() {
        return conditions.stream()
                .map(ConditionExpression::toSOQL)
                .collect(Collectors.joining(" " + operator.getSymbol() + " ", "(", ")"));
    }
}
