package com.noggy.assistant.cooking.dao.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Builder
@Value
@AllArgsConstructor // Needed because @Builder needs an all field constructor and the one provided by @Value is overridden by our custom one
public class CalendarEvent {

    @JsonProperty("_id")
    UUID calendarEventId;

    Instant start;

    Instant end;

    Schedule schedule;

    UUID userId;

    // Don't want a builder since all fields are required
    public CalendarEvent(UUID calendarEventId, Instant start, Schedule schedule, UUID userId) {
        this.calendarEventId = calendarEventId;
        this.schedule = schedule;
        this.userId = userId;

        this.start = start;
        int minutesFromStart = 30 * schedule.getMeals().get(schedule.getMeals().size() - 1).getEpoch();
        this.end = start.plus(minutesFromStart, ChronoUnit.MINUTES);
    }

}
