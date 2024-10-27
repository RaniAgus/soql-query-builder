package io.github.raniagus.soqlquery.query.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

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
}
