package com.noggy.assistant.cooking.dao.model;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class Meal {

    String name;

    // 30 min epoch
    // Defines the moment in the schedule when this particular meal should happen
    int epoch;

    List<String> ingredients;

}
