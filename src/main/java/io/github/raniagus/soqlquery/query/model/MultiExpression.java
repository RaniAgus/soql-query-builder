package io.github.raniagus.soqlquery.query.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class MultiExpression implements ConditionExpression {
    Operator operator;

    @NotNull @Size(min = 2) @Valid
    List<ConditionExpression> conditions;

    @Override
    public String getExpression() {
        return conditions.stream()
                .map(ConditionExpression::getExpression)
                .collect(Collectors.joining(" " + operator.getSymbol() + " ", "(", ")"));
    }
}
