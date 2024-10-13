package io.github.raniagus.soqlquery.query.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
    String toExpression();

    static ConditionExpression or(ConditionExpression... conditions) {
        return new MultiExpression(Operator.OR, List.of(conditions));
    }

    static ConditionExpression and(ConditionExpression... conditions) {
        return new MultiExpression(Operator.AND, List.of(conditions));
    }

    static ConditionExpression not(ConditionExpression condition) {
        return new UnaryExpression(Operator.NOT, condition);
    }

    static ConditionExpression eq(String field, Object value) {
        return new FieldExpression<>(Operator.EQUALS, field, value);
    }

    static ConditionExpression ne(String field, Object value) {
        return new FieldExpression<>(Operator.NOT_EQUALS, field, value);
    }

    static ConditionExpression gt(String field, Object value) {
        return new FieldExpression<>(Operator.GREATER_THAN, field, value);
    }

    static ConditionExpression lt(String field, Object value) {
        return new FieldExpression<>(Operator.LESS_THAN, field, value);
    }

    static ConditionExpression gte(String field, Object value) {
        return new FieldExpression<>(Operator.GREATER_THAN_OR_EQUALS, field, value);
    }

    static ConditionExpression lte(String field, Object value) {
        return new FieldExpression<>(Operator.LESS_THAN_OR_EQUALS, field, value);
    }

    static ConditionExpression in(String field, Object... values) {
        return new FieldInExpression(Operator.IN, field, List.of(values));
    }
}
