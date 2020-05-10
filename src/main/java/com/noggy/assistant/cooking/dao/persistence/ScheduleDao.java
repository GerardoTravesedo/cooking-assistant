package com.noggy.assistant.cooking.dao.persistence;

import com.noggy.assistant.cooking.dao.model.Schedule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ScheduleDao {

    List<Schedule> getAllSchedules(UUID userId);

    Optional<Schedule> getSchedule(UUID id);

    void insertSchedule(Schedule schedule);

    void deleteSchedule(UUID id);

    void deleteAll(UUID userId);

}
