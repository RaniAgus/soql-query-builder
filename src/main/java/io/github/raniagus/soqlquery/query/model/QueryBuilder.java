package io.github.raniagus.soqlquery.query.model;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class QueryBuilder {
    public static ConditionExpression or(ConditionExpression... conditions) {
        return new MultiExpression(Operator.OR, List.of(conditions));
    }

    public static ConditionExpression or(Collection<ConditionExpression> conditions) {
        return new MultiExpression(Operator.OR, List.copyOf(conditions));
    }

    public static ConditionExpression and(ConditionExpression... conditions) {
        return new MultiExpression(Operator.AND, List.of(conditions));
    }

    public static ConditionExpression and(Collection<ConditionExpression> conditions) {
        return new MultiExpression(Operator.AND, List.copyOf(conditions));
    }

    public static ConditionExpression not(ConditionExpression condition) {
        return new UnaryExpression(Operator.NOT, condition);
    }

    public static ConditionExpression eq(String field, String value) {
        return new FieldExpression<>(Operator.EQUALS, field, value);
    }

    public static ConditionExpression eq(String field, Number value) {
        return new FieldExpression<>(Operator.EQUALS, field, value);
    }

    public static ConditionExpression eq(String field, Boolean value) {
        return new FieldExpression<>(Operator.EQUALS, field, value);
    }

    public static ConditionExpression ne(String field, String value) {
        return new FieldExpression<>(Operator.NOT_EQUALS, field, value);
    }

    public static ConditionExpression ne(String field, Number value) {
        return new FieldExpression<>(Operator.NOT_EQUALS, field, value);
    }

    public static ConditionExpression ne(String field, Boolean value) {
        return new FieldExpression<>(Operator.NOT_EQUALS, field, value);
    }

    public static ConditionExpression isNull(String field) {
        return new FieldExpression<>(Operator.EQUALS, field, null);
    }

    public static ConditionExpression isNotNull(String field) {
        return new FieldExpression<>(Operator.NOT_EQUALS, field, null);
    }

    public static ConditionExpression gt(String field, String value) {
        return new FieldExpression<>(Operator.GREATER_THAN, field, value);
    }

    public static ConditionExpression gt(String field, Number value) {
        return new FieldExpression<>(Operator.GREATER_THAN, field, value);
    }

    public static ConditionExpression gt(String field, Instant value) {
        return new FieldExpression<>(Operator.GREATER_THAN, field, DateTimeFormatter.ISO_INSTANT.format(value));
    }

    public static ConditionExpression lt(String field, String value) {
        return new FieldExpression<>(Operator.LESS_THAN, field, value);
    }

    public static ConditionExpression lt(String field, Number value) {
        return new FieldExpression<>(Operator.LESS_THAN, field, value);
    }

    public static ConditionExpression lt(String field, Instant value) {
        return new FieldExpression<>(Operator.LESS_THAN, field, DateTimeFormatter.ISO_INSTANT.format(value));
    }

    public static ConditionExpression gte(String field, String value) {
        return new FieldExpression<>(Operator.GREATER_THAN_OR_EQUALS, field, value);
    }

    public static ConditionExpression gte(String field, Number value) {
        return new FieldExpression<>(Operator.GREATER_THAN_OR_EQUALS, field, value);
    }

    public static ConditionExpression gte(String field, Instant value) {
        return new FieldExpression<>(Operator.GREATER_THAN_OR_EQUALS, field, DateTimeFormatter.ISO_INSTANT.format(value));
    }

    public static ConditionExpression lte(String field, String value) {
        return new FieldExpression<>(Operator.LESS_THAN_OR_EQUALS, field, value);
    }

    public static ConditionExpression lte(String field, Number value) {
        return new FieldExpression<>(Operator.LESS_THAN_OR_EQUALS, field, value);
    }

    public static ConditionExpression lte(String field, Instant value) {
        return new FieldExpression<>(Operator.LESS_THAN_OR_EQUALS, field, DateTimeFormatter.ISO_INSTANT.format(value));
    }

    public static ConditionExpression in(String field, String... values) {
        return new FieldInExpression(Operator.IN, field, List.of(values));
    }
}
