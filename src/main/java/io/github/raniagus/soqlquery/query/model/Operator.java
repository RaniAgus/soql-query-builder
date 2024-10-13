package io.github.raniagus.soqlquery.query.model;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Operator {
    OR("OR"),
    AND("AND"),
    NOT("NOT"),
    EQUALS("="),
    NOT_EQUALS("!="),
    GREATER_THAN(">"),
    LESS_THAN("<"),
    GREATER_THAN_OR_EQUALS(">="),
    LESS_THAN_OR_EQUALS("<="),
    IN("IN");

    @Getter(onMethod_ = @JsonValue)
    public final String symbol;
}
