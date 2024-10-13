package io.github.raniagus.soqlquery.query.dto;

import io.github.raniagus.soqlquery.query.model.ConditionExpression;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record QueryObjectsRequest(@NotNull @Valid ConditionExpression where) {
}
