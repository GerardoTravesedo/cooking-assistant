package com.noggy.assistant.cooking.dao.persistence;

import com.noggy.assistant.cooking.dao.model.CalendarEvent;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CalendarEventDao {

    Optional<CalendarEvent> getCalendarEvent(UUID id);

    List<CalendarEvent> getCalendarEventsInTimeWindow(UUID userId, Instant start, Instant end);

    void insertCalendarEvent(CalendarEvent calendarEvent);

    void deleteCalendarEvent(UUID id);

}
