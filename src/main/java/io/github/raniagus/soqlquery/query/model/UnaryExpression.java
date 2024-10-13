package io.github.raniagus.soqlquery.query.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record UnaryExpression(
        Operator operator,
        @NotNull @Valid ConditionExpression condition
) implements ConditionExpression {
    @Override
    public String toExpression() {
        return "(" + operator.getSymbol() + " " + condition.toExpression() + ")";
    }
}
