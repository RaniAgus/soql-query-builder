package io.github.raniagus.soqlquery.query.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueryUtilsTest {
    @Test
    void formatStringWithSingleQuote() {
        String formatted = QueryUtils.formatValue("value");
        assertEquals("'value'", formatted);
    }

    @Test
    void formatStringWithSOQLInjection() {
        String formatted = QueryUtils.formatValue("value' OR '1'='1");
        assertEquals("'value\\' OR \\'1\\'=\\'1'", formatted);
    }

    @Test
    void formatStringWithNull() {
        String formatted = QueryUtils.formatValue(null);
        assertEquals("NULL", formatted);
    }

    @Test
    void formatStringWithBoolean() {
        String formatted = QueryUtils.formatValue(true);
        assertEquals("true", formatted);
    }

    @Test
    void formatStringWithNumber() {
        String formatted = QueryUtils.formatValue(1);
        assertEquals("1", formatted);
    }
}