package com.example.monitoring.utilities;

import java.util.List;

public record PageResponse<T>(List<T> content, boolean hasNext, String nextCursor, int total) {
    public PageResponse(List<T> content, boolean hasNext) {
        this(content, hasNext, null, 0);
    }

    public PageResponse(List<T> content, boolean hasNext, int total) {
        this(content, hasNext, null, total);
    }
}