package io.github.raniagus.soqlquery.query.model;

import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QueryBuilderTest {

    @Test
    void testOr() {
        ConditionExpression condition1 = QueryBuilder.eq("field1", "value1");
        ConditionExpression condition2 = QueryBuilder.eq("field2", "value2");
        ConditionExpression orCondition = QueryBuilder.or(condition1, condition2);

        assertEquals("((field1 = 'value1') OR (field2 = 'value2'))", orCondition.toSOQL());
    }

    @Test
    void testAnd() {
        ConditionExpression condition1 = QueryBuilder.eq("field1", "value1");
        ConditionExpression condition2 = QueryBuilder.eq("field2", "value2");
        ConditionExpression andCondition = QueryBuilder.and(condition1, condition2);

        assertEquals("((field1 = 'value1') AND (field2 = 'value2'))", andCondition.toSOQL());
    }

    @Test
    void testNot() {
        ConditionExpression condition = QueryBuilder.eq("field", "value");
        ConditionExpression notCondition = QueryBuilder.not(condition);

        assertEquals("(NOT (field = 'value'))", notCondition.toSOQL());
    }

    @Test
    void testEq() {
        ConditionExpression eqCondition = QueryBuilder.eq("field", "value");

        assertEquals("(field = 'value')", eqCondition.toSOQL());
    }

    @Test
    void testNe() {
        ConditionExpression neCondition = QueryBuilder.ne("field", "value");

        assertEquals("(field != 'value')", neCondition.toSOQL());
    }

    @Test
    void testIsNull() {
        ConditionExpression isNullCondition = QueryBuilder.isNull("field");

        assertEquals("(field = NULL)", isNullCondition.toSOQL());
    }

    @Test
    void testIsNotNull() {
        ConditionExpression isNotNullCondition = QueryBuilder.isNotNull("field");

        assertEquals("(field != NULL)", isNotNullCondition.toSOQL());
    }

    @Test
    void testGt() {
        ConditionExpression gtCondition = QueryBuilder.gt("field", "value");

        assertEquals("(field > 'value')", gtCondition.toSOQL());
    }

    @Test
    void testLt() {
        ConditionExpression ltCondition = QueryBuilder.lt("field", "value");

        assertEquals("(field < 'value')", ltCondition.toSOQL());
    }

    @Test
    void testGte() {
        ConditionExpression gteCondition = QueryBuilder.gte("field", "value");

        assertEquals("(field >= 'value')", gteCondition.toSOQL());
    }

    @Test
    void testLte() {
        ConditionExpression lteCondition = QueryBuilder.lte("field", "value");

        assertEquals("(field <= 'value')", lteCondition.toSOQL());
    }

    @Test
    void testIn() {
        ConditionExpression inCondition = QueryBuilder.in("field", "value1", "value2");

        assertEquals("(field IN ('value1','value2'))", inCondition.toSOQL());
    }

    @Test
    void testGtInstant() {
        Instant instant = Instant.ofEpochSecond(0);
        ConditionExpression gtCondition = QueryBuilder.gt("field", instant);

        assertEquals("(field > '1970-01-01T00:00:00Z')", gtCondition.toSOQL());
    }

    @Test
    void testLtInstant() {
        Instant instant = Instant.ofEpochSecond(0);
        ConditionExpression ltCondition = QueryBuilder.lt("field", instant);

        assertEquals("(field < '1970-01-01T00:00:00Z')", ltCondition.toSOQL());
    }

    @Test
    void testGteInstant() {
        Instant instant = Instant.ofEpochSecond(0);
        ConditionExpression gteCondition = QueryBuilder.gte("field", instant);

        assertEquals("(field >= '1970-01-01T00:00:00Z')", gteCondition.toSOQL());
    }

    @Test
    void testLteInstant() {
        Instant instant = Instant.ofEpochSecond(0);
        ConditionExpression lteCondition = QueryBuilder.lte("field", instant);

        assertEquals("(field <= '1970-01-01T00:00:00Z')", lteCondition.toSOQL());
    }
}