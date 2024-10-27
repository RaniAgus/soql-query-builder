package io.github.raniagus.soqlquery.query.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

record UnaryExpression(
        Operator operator,
        @NotNull @Valid ConditionExpression condition
) implements ConditionExpression {
    @Override
    public String toSOQL() {
        return "(" + operator.getSymbol() + " " + condition.toSOQL() + ")";
    }
}
