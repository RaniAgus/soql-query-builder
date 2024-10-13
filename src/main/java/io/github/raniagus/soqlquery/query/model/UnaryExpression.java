package io.github.raniagus.soqlquery.query.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class UnaryExpression implements ConditionExpression {
    Operator operator;

    @NotNull @Valid
    ConditionExpression condition;

    @Override
    public String getExpression() {
        return "(" + operator.getSymbol() + " " + condition.getExpression() + ")";
    }
}
