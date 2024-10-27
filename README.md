# soql-query

A simple tool to build SOQL queries from a JSON object.

## Examples

### Request using curl

```sh
curl -X POST http://localhost:8080/objects/Account \
-H "Content-Type: application/json" \
-d '{
  "where": {
    "operator": "AND",
    "conditions": [
      { "operator": "=", "field": "Name", "value": "Test" },
      { "operator": ">", "field": "CreatedDate", "value": "2024-10-27T00:00:00Z" },
      {
        "operator": "NOT",
        "condition": { "operator": "IN", "field": "Type", "values": ["Customer", "Partner"] }
      }
    ]
  }
}'
```

```sql
SELECT Id,Name,CreatedDate,LastModifiedDate FROM Account WHERE ((Name = 'Test') AND (CreatedDate > '2024-10-27T00:00:00Z') AND (NOT (Type IN ('Customer','Partner'))))
```

### Building where clause from Java

```java
import static io.github.raniagus.soqlquery.query.model.QueryBuilder.*;

void main() {
    var where = and(
            eq("Name", "Test"),
            gt("CreatedDate", Instant.now()),
            not(in("Type", "Customer", "Partner"))
    );

    println(where.toSOQL());
}
```
```sql
((Name = 'Test') AND (CreatedDate > '2024-10-27T00:00:00Z') AND (NOT (Type IN ('Customer','Partner'))))
```
