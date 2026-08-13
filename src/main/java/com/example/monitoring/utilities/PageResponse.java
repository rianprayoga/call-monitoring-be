package com.example.monitoring.utilities;

import java.util.List;

public record PageResponse<T>(List<T> data, boolean hasNext, String nextCursor) { }