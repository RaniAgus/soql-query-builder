# soql-query

A simple tool to build SOQL queries from a JSON object.

## Example

```sh
POST /objects/Contact
{
    "where": {
        "operator": "AND",
        "conditions": [
            {
                "operator": "NOT",
                "condition": {
                    "operator": "=",
                    "field": "Name",
                    "value": "Test"
                }
            },
            {
                "operator": ">",
                "field": "CreatedDate",
                "value": "2021-01-01T00:00:00Z"
            },
            {
                "operator": "IN",
                "field": "Type",
                "values": [
                    "Customer",
                    "Partner"
                ]
            }
        ]
    }
}
```

```sql
SELECT Id,Name,Email,CreatedDate,LastModifiedDate FROM Contact WHERE ((NOT (Name = 'Test')) AND (CreatedDate > '2021-01-01T00:00:00Z') AND (Type IN ('Customer','Partner')))
```