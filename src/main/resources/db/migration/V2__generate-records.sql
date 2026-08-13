START TRANSACTION;

INSERT INTO call_sentiments
    (call_timestamp, customer_service_name, customer_name,customer_sentiment)
	SELECT
        NOW() + INTERVAL '1 minutes',
        concat('cs gibson ', i),
        concat('customer rabson ', i),
        i * 10
    FROM generate_series(1, 10) as i;

COMMIT;