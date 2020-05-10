package com.noggy.assistant.cooking.dao.persistence;

import com.noggy.assistant.cooking.dao.model.Dish;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishDao {

    void insertDish(Dish dish);

    Optional<Dish> getDish(UUID id);

    List<Dish> getDishesWithNameLike(String nameLike);

}
