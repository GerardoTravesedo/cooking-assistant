package com.noggy.assistant.cooking.dao.persistence;

import com.google.common.collect.ImmutableList;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.noggy.assistant.cooking.dao.db.MongoConnectionManager;
import com.noggy.assistant.cooking.dao.model.CalendarEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MongoCalendarEventDao implements CalendarEventDao {

    private static final String COLLECTION = "CalendarEvents";

    private static final String ID_FIELD = "_id";

    private static final String USER_ID_FIELD = "userId";

    private static final String START_FIELD = "start";

    private static final String END_FIELD = "end";

    private final MongoCollection<CalendarEvent> collection;

    @Autowired
    public MongoCalendarEventDao(MongoConnectionManager connectionManager) {
        this.collection = connectionManager.getRunner(COLLECTION, CalendarEvent.class);
    }

    @Override
    public Optional<CalendarEvent> getCalendarEvent(UUID id) {
        return Optional.ofNullable(collection.find(Filters.eq(ID_FIELD, id)).first());
    }

    @Override
    public List<CalendarEvent> getCalendarEventsInTimeWindow(UUID userId, Instant start, Instant end) {
        return ImmutableList.copyOf(
                collection.find(Filters.and(
                    Filters.eq(USER_ID_FIELD, userId),
                    Filters.gt(END_FIELD, start),
                    Filters.lt(START_FIELD, end)))
        );
    }

    @Override
    public void insertCalendarEvent(CalendarEvent calendarEvent) {
        collection.insertOne(calendarEvent);
    }

    @Override
    public void deleteCalendarEvent(UUID id) {
        collection.deleteOne(Filters.eq(ID_FIELD, id));
    }

}
