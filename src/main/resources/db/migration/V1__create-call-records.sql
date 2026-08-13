CREATE TABLE call_sentiments(
    call_id UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    call_timestamp TIMESTAMP NOT NULL,
    customer_service_name VARCHAR(100) NOT NULL,
    customer_name VARCHAR(100) NOT NULL,
    customer_sentiment NUMERIC(5,2) NOT NULL CHECK (
        customer_sentiment >= 000.00 AND customer_sentiment < 100.00)
);
