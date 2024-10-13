package io.github.raniagus.soqlquery.query.util;

import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

@UtilityClass
public class QueryUtils {
    public static String formatValue(Object value) {
        if (value instanceof Number || value instanceof Boolean) {
            return value.toString();
        } else if (value instanceof String str) {
            return "'" + StringUtils.replace(str, "'", "\\'") + "'";
        } else {
            return "NULL";
        }
    }
}
