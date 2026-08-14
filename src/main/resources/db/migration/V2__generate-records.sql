INSERT INTO call_sentiments (call_timestamp, customer_service_name, customer_name, customer_sentiment)
SELECT
    CURRENT_DATE + (n - 1) * INTERVAL '1 day' + (random() * INTERVAL '1 day'),
    (ARRAY['Alice Johnson', 'Brian Smith', 'Carla Nguyen', 'Derek Patel'])[floor(random() * 4 + 1)],
    (ARRAY['Michael Chen', 'Sarah Williams', 'David Rodriguez', 'Emily Davis',
           'James Wilson', 'Olivia Martinez', 'Daniel Anderson', 'Sophia Thomas',
           'Ethan Jackson', 'Ava White'])[floor(random() * 10 + 1)],
    round((random() * 100)::numeric, 2)
FROM generate_series(1, 10) AS n;


INSERT INTO call_sentiments (call_timestamp, customer_service_name, customer_name, customer_sentiment)
    values
    	(now() - interval '3 months', 'Smith', 'Daniel', 20),
        (now() - interval '3 months', 'Nguyen', 'Smith', 19),
        (now() - interval '3 months', 'Daren', 'Patel', 78.2),
        (now() - interval '4 months', 'Daren', 'Patel', 54.2);