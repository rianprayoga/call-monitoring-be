## How to run
prerequisite:
* java 17
* maven 3.9.9
* postgres 14 (username and password: postgres)
* bruno (to run manual test)

Get inside main folder then run:
```
mvn spring-boot:run
```

## Database design
The ideal should be:
* customers_table
  
    | customer_id | name |
    | ------------- |:-------------:|
    | 1      |  foo     |
    | 2      |  bar     |
* customer_services_table

    | cs_id | name |
    | ------------- |:-------------:|
    | 1      |  bun     |
    | 2      |  di     |

* call_sentiments_table
  
    | call_id | customer_id |cs_id |call_timestamp|sentiment_score|
    | ---- |:-----:|:-----:|:-----:|:-----:|
    | 1 |1|2|xxxxx|90|

    customers_table **1..n** call_sentiments_table

    customer_services_table **1..n** call_sentiments_table

For simplicity I made it looks like this:

| call_id | customer_service_name |customer_name  |call_timestamp|customer_sentiment|
| ---- |:-----:|:-----:|:-----:|:-----:|
| 1 |bun|foo|xxx|90|

The script can be seen in

## The endpoint parameter

the API http://localhost:8080/v1/sentiments

| Query param | Desc |
| ------------- |:-------------:|
| query      |  Too search value in any column.     |
| min      |  The minimum value of sentiment to be displayed, **x >= min** will be displayed.    |
| max      |  The max value of sentiment that will be displayed,   **x <= max** will be displayed. Min and max can be used together to make range ( min <= x <= max ). |
| start      |  Start value to filter call timestamp.    |
| end      |  End value to filter call timestamp. Start and end must be used together.     |
| period      |  To display call sentiments from **period** to 3 months before **period**.     |
| size      |  Size of the result.     |
| cursor      |  To go to the next page.     |
