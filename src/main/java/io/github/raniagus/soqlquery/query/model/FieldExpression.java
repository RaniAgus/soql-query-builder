package io.github.raniagus.soqlquery.query.model;

import io.github.raniagus.soqlquery.query.util.QueryUtils;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Value;

@Value
public class FieldExpression<T> implements ConditionExpression {
    Operator operator;

    @NotNull
    @Pattern(regexp = "^\\w+$")
    String field;

    T value;

    @Override
    public String toExpression() {
        return "(" + field + " " + operator.getSymbol() + " " + QueryUtils.formatValue(value) + ")";
    }
}
