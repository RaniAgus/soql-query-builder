# soql-query

A simple tool to build SOQL queries from a JSON object.

## Example

```sh
curl -X POST http://localhost:8080/objects/Account \
-H "Content-Type: application/json" \
-d '{
  "where": {
    "operator": "AND",
    "conditions": [
      { "operator": "=", "field": "Name", "value": "Test" },
      { "operator": ">", "field": "CreatedDate", "value": "2021-01-01T00:00:00Z" },
      {
        "operator": "NOT",
        "condition": { "operator": "IN", "field": "Type", "values": ["Customer", "Partner"] }
      }
    ]
  }
}'
```

```sql
SELECT Id,Name,CreatedDate,LastModifiedDate FROM Account WHERE ((Name = 'Test') AND (CreatedDate > '2021-01-01T00:00:00Z') AND (NOT (Type IN ('Customer','Partner'))))
```
