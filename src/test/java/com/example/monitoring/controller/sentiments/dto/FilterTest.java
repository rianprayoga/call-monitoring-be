package com.example.monitoring.controller.sentiments.dto;

import com.example.monitoring.errors.http.BadRequestException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.OffsetDateTime;

public class FilterTest {

    @Test
    public void filer_createdSuccessfully(){
        Filter filter = Filter.of(12, 13, null, null, null, "what");
        Assertions.assertThat(filter)
                .returns(12.0, Filter::min)
                .returns(13.0, Filter::max)
                .returns(null, Filter::start)
                .returns(null, Filter::end)
                .returns(null, Filter::period)
                .returns("what", Filter::cursor);

        filter = Filter.of(12, 13, "2026-08-13T11:06:50.942923Z", "2026-08-13T11:06:50.942923Z", null, "what");
        Assertions.assertThat(filter)
                .returns(12.0, Filter::min)
                .returns(13.0, Filter::max)
                .returns(OffsetDateTime.parse("2026-08-13T11:06:50.942923Z"), Filter::start)
                .returns(OffsetDateTime.parse("2026-08-13T11:06:50.942923Z"), Filter::end)
                .returns(null, Filter::period)
                .returns("what", Filter::cursor);

        filter = Filter.of(12, 13, null, null, "2026-08-13T11:06:50.942923Z", "what");
        Assertions.assertThat(filter)
                .returns(12.0, Filter::min)
                .returns(13.0, Filter::max)
                .returns(null, Filter::start)
                .returns(null, Filter::end)
                .returns(OffsetDateTime.parse("2026-08-13T11:06:50.942923Z"), Filter::period)
                .returns("what", Filter::cursor);
    }

    @Test
    public void filter_periodCantBeUsedWithAnotherTimeFilter() {

        Assertions.assertThatThrownBy(() -> Filter.of(
                        1,
                        2,
                        "2026-08-13T11:06:50.942923Z",
                        "2026-08-13T11:06:50.942923Z",
                        "2026-08-13T11:06:50.942923Z",
                        "what"))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Period can't be used together with start or end.");

        Assertions.assertThatThrownBy(() ->
                        Filter.of(1, 2, "2026-08-13T11:06:50.942923Z", null, "2026-08-13T11:06:50.942923Z", "what"))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Period can't be used together with start or end.");

        Assertions.assertThatThrownBy(() ->
                        Filter.of(1, 2, null, "2026-08-13T11:06:50.942923Z", "2026-08-13T11:06:50.942923Z", "what"))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Period can't be used together with start or end.");
    }

    @Test
    public void filter_startAndEndMustBeTogether() {

        Assertions.assertThatThrownBy(() -> Filter.of(1, 2, "2026-08-13T11:06:50.942923Z", null, null, "what"))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Start and End must be used together.");

        Assertions.assertThatThrownBy(() -> Filter.of(1, 2, null, "2026-08-13T11:06:50.942923Z", null, "what"))
                .isInstanceOf(BadRequestException.class)
                .hasMessage("Start and End must be used together.");

    }
}
