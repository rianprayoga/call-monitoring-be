package com.example.monitoring.data.repository;

import com.example.monitoring.data.entity.CallSentiment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CallSentimentRepo extends JpaRepository<CallSentiment, UUID> {

    @Query(value = """
    SELECT *
    FROM call_sentiments
    WHERE call_id = :id
    """, nativeQuery = true)
    Optional<CallSentiment> getSentimentBy(@Param("id") UUID id);

    @Query(value = """
            SELECT *
            FROM call_sentiments
            WHERE
                (cast(:start as timestamp) is null or call_timestamp >= :start) AND
                (cast(:end as timestamp) is null or call_timestamp <= :end) AND
                (:min is null or customer_sentiment >= :min) AND
                (:max is null or customer_sentiment <= :max)
            ORDER BY call_timestamp, call_id DESC
            LIMIT :size+1
            """, nativeQuery = true)
    List<CallSentiment> getSentiments(
            @Param("size") Integer size,
            @Param("min") Double min,
            @Param("max") Double max,
            @Param("start") OffsetDateTime start,
            @Param("end") OffsetDateTime end);

    @Query(value = """
            SELECT *
            FROM call_sentiments
            WHERE (call_timestamp,call_id ) > (:timestamp, :id)
            ORDER BY call_timestamp, call_id DESC
            LIMIT :size+1
            """, nativeQuery = true)
    List<CallSentiment> getSentiments(
            @Param("id") UUID id,
            @Param("timestamp") OffsetDateTime timestamp,
            @Param("size") Integer size);


}
