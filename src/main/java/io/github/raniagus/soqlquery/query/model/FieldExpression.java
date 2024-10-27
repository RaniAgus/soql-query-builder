package io.github.raniagus.soqlquery.query.model;

import io.github.raniagus.soqlquery.query.util.QueryUtils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

record FieldExpression<T>(
        Operator operator,
        @NotNull @Pattern(regexp = "^\\w+$") String field,
        T value
) implements ConditionExpression {
    @Override
    public String toSOQL() {
        return "(" + field + " " + operator.getSymbol() + " " + QueryUtils.formatValue(value) + ")";
    }
}
