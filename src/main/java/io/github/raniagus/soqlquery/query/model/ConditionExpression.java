package io.github.raniagus.soqlquery.query.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "operator", visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = MultiExpression.class, name = "OR"),
        @JsonSubTypes.Type(value = MultiExpression.class, name = "AND"),
        @JsonSubTypes.Type(value = UnaryExpression.class, name = "NOT"),
        @JsonSubTypes.Type(value = FieldExpression.class, name = "="),
        @JsonSubTypes.Type(value = FieldExpression.class, name = "!="),
        @JsonSubTypes.Type(value = FieldExpression.class, name = ">"),
        @JsonSubTypes.Type(value = FieldExpression.class, name = "<"),
        @JsonSubTypes.Type(value = FieldExpression.class, name = ">="),
        @JsonSubTypes.Type(value = FieldExpression.class, name = "<="),
        @JsonSubTypes.Type(value = FieldInExpression.class, name = "IN"),
})
public interface ConditionExpression {
    Operator operator();
    String toSOQL();

    static ConditionExpression or(ConditionExpression... conditions) {
        return new MultiExpression(Operator.OR, List.of(conditions));
    }

    static ConditionExpression or(Collection<ConditionExpression> conditions) {
        return new MultiExpression(Operator.OR, List.copyOf(conditions));
    }

    static ConditionExpression and(ConditionExpression... conditions) {
        return new MultiExpression(Operator.AND, List.of(conditions));
    }

    static ConditionExpression and(Collection<ConditionExpression> conditions) {
        return new MultiExpression(Operator.AND, List.copyOf(conditions));
    }

    static ConditionExpression not(ConditionExpression condition) {
        return new UnaryExpression(Operator.NOT, condition);
    }

    static ConditionExpression eq(String field, String value) {
        return new FieldExpression<>(Operator.EQUALS, field, value);
    }

    static ConditionExpression eq(String field, Number value) {
        return new FieldExpression<>(Operator.EQUALS, field, value);
    }

    static ConditionExpression eq(String field, Boolean value) {
        return new FieldExpression<>(Operator.EQUALS, field, value);
    }

    static ConditionExpression ne(String field, String value) {
        return new FieldExpression<>(Operator.NOT_EQUALS, field, value);
    }

    static ConditionExpression ne(String field, Number value) {
        return new FieldExpression<>(Operator.NOT_EQUALS, field, value);
    }

    static ConditionExpression ne(String field, Boolean value) {
        return new FieldExpression<>(Operator.NOT_EQUALS, field, value);
    }

    static ConditionExpression isNull(String field) {
        return new FieldExpression<>(Operator.EQUALS, field, null);
    }

    static ConditionExpression isNotNull(String field) {
        return new FieldExpression<>(Operator.NOT_EQUALS, field, null);
    }

    static ConditionExpression gt(String field, String value) {
        return new FieldExpression<>(Operator.GREATER_THAN, field, value);
    }

    static ConditionExpression gt(String field, Number value) {
        return new FieldExpression<>(Operator.GREATER_THAN, field, value);
    }

    static ConditionExpression gt(String field, Instant value) {
        return new FieldExpression<>(Operator.GREATER_THAN, field, DateTimeFormatter.ISO_INSTANT.format(value));
    }

    static ConditionExpression lt(String field, String value) {
        return new FieldExpression<>(Operator.LESS_THAN, field, value);
    }

    static ConditionExpression lt(String field, Number value) {
        return new FieldExpression<>(Operator.LESS_THAN, field, value);
    }

    static ConditionExpression lt(String field, Instant value) {
        return new FieldExpression<>(Operator.LESS_THAN, field, DateTimeFormatter.ISO_INSTANT.format(value));
    }

    static ConditionExpression gte(String field, String value) {
        return new FieldExpression<>(Operator.GREATER_THAN_OR_EQUALS, field, value);
    }

    static ConditionExpression gte(String field, Number value) {
        return new FieldExpression<>(Operator.GREATER_THAN_OR_EQUALS, field, value);
    }

    static ConditionExpression gte(String field, Instant value) {
        return new FieldExpression<>(Operator.GREATER_THAN_OR_EQUALS, field, DateTimeFormatter.ISO_INSTANT.format(value));
    }

    static ConditionExpression lte(String field, String value) {
        return new FieldExpression<>(Operator.LESS_THAN_OR_EQUALS, field, value);
    }

    static ConditionExpression lte(String field, Number value) {
        return new FieldExpression<>(Operator.LESS_THAN_OR_EQUALS, field, value);
    }

    static ConditionExpression lte(String field, Instant value) {
        return new FieldExpression<>(Operator.LESS_THAN_OR_EQUALS, field, DateTimeFormatter.ISO_INSTANT.format(value));
    }

    static ConditionExpression in(String field, String... values) {
        return new FieldInExpression(Operator.IN, field, List.of(values));
    }
}
