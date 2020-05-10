package com.noggy.assistant.cooking.dao.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Builder
@Value
public class Schedule {

    @JsonProperty("_id")
    UUID scheduleId;

    UUID userId;

    List<Meal> meals;

}
