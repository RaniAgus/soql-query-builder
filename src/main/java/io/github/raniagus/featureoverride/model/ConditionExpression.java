package io.github.raniagus.featureoverride.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.List;
import java.util.function.Predicate;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "operator")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ConditionExpression.Or.class, name = "OR"),
        @JsonSubTypes.Type(value = ConditionExpression.And.class, name = "AND"),
        @JsonSubTypes.Type(value = ConditionExpression.Not.class, name = "NOT"),
        @JsonSubTypes.Type(value = ConditionExpression.Equals.class, name = "="),
        @JsonSubTypes.Type(value = ConditionExpression.NotEquals.class, name = "!="),
        @JsonSubTypes.Type(value = ConditionExpression.LessThan.class, name = "<"),
        @JsonSubTypes.Type(value = ConditionExpression.LessThanOrEquals.class, name = "<="),
        @JsonSubTypes.Type(value = ConditionExpression.GreaterThan.class, name = ">"),
        @JsonSubTypes.Type(value = ConditionExpression.GreaterThanOrEquals.class, name = ">="),
        @JsonSubTypes.Type(value = ConditionExpression.In.class, name = "IN"),
})
public sealed interface ConditionExpression extends Predicate<Context> {
    record Or(List<ConditionExpression> conditions) implements ConditionExpression {
        @Override
        public boolean test(Context value) {
            return conditions.stream().anyMatch(condition -> condition.test(value));
        }
    }

    record And(List<ConditionExpression> conditions) implements ConditionExpression {
        @Override
        public boolean test(Context value) {
            return conditions.stream().allMatch(condition -> condition.test(value));
        }
    }

    record Not(ConditionExpression condition) implements ConditionExpression {
        @Override
        public boolean test(Context value) {
            return !condition.test(value);
        }
    }

    record Equals(Field field) implements ConditionExpression {
        @Override
        public boolean test(Context context) {
            return context.isEqualTo(field);
        }
    }

    record NotEquals(Field field) implements ConditionExpression {
        @Override
        public boolean test(Context context) {
            return !context.isEqualTo(field);
        }
    }

    record LessThan(Field field) implements ConditionExpression {
        @Override
        public boolean test(Context context) {
            return context.compareTo(field) < 0;
        }
    }

    record LessThanOrEquals(Field field) implements ConditionExpression {
        @Override
        public boolean test(Context context) {
            return context.compareTo(field) < 0 || context.isEqualTo(field);
        }
    }

    record GreaterThan(Field field) implements ConditionExpression {
        @Override
        public boolean test(Context context) {
            return context.compareTo(field) > 0;
        }
    }

    record GreaterThanOrEquals(Field field) implements ConditionExpression {
        @Override
        public boolean test(Context context) {
            return context.compareTo(field) > 0 || context.isEqualTo(field);
        }
    }

    record In(InField field) implements ConditionExpression {
        record InField(String name, List<String> values) {}

        @Override
        public boolean test(Context context) {
            return field.values().stream().anyMatch(value -> context.contains(field.name(), value));
        }
    }
}
