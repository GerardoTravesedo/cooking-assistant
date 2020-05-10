package com.noggy.assistant.cooking.dao.persistence;

import com.google.common.collect.ImmutableList;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.noggy.assistant.cooking.dao.db.MongoConnectionManager;
import com.noggy.assistant.cooking.dao.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MongoScheduleDao implements ScheduleDao {

    private static final String COLLECTION = "Schedules";

    private static final String ID_FIELD = "_id";

    private static final String USER_ID_FIELD = "userId";

    private final MongoCollection<Schedule> collection;

    @Autowired
    public MongoScheduleDao(MongoConnectionManager connectionManager) {
        this.collection = connectionManager.getRunner(COLLECTION, Schedule.class);
    }

    @Override
    public List<Schedule> getAllSchedules(UUID userId) {
        return ImmutableList.copyOf(collection.find(Filters.eq(USER_ID_FIELD, userId)));
    }

    @Override
    public Optional<Schedule> getSchedule(UUID id) {
        return Optional.ofNullable(collection.find(Filters.eq(ID_FIELD, id)).first());
    }

    @Override
    public void insertSchedule(Schedule schedule) {
        collection.insertOne(schedule);
    }

    @Override
    public void deleteSchedule(UUID id) {
        collection.deleteOne(Filters.eq(ID_FIELD, id));
    }

    @Override
    public void deleteAll(UUID userId) {
        collection.deleteMany(Filters.eq(USER_ID_FIELD, userId));
    }

}
