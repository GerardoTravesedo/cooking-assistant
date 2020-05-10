package com.noggy.assistant.cooking.dao.persistence;

import com.google.common.collect.ImmutableList;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.noggy.assistant.cooking.dao.db.MongoConnectionManager;
import com.noggy.assistant.cooking.dao.model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

@Component
public class MongoDishDao implements DishDao {

    private static final String COLLECTION = "Dishes";

    private static final String ID_FIELD = "_id";

    private static final String NAME_FIELD = "name";

    private final MongoCollection<Dish> collection;

    @Autowired
    public MongoDishDao(MongoConnectionManager connectionManager) {
        this.collection = connectionManager.getRunner(COLLECTION, Dish.class);
    }

    @Override
    public void insertDish(Dish dish) {
        collection.insertOne(dish);
    }

    @Override
    public Optional<Dish> getDish(UUID id) {
        return Optional.ofNullable(collection.find(Filters.eq(ID_FIELD, id)).first());
    }

    @Override
    public List<Dish> getDishesWithNameLike(String nameLike) {
        String expression = "*" + nameLike + "*";
        return ImmutableList.copyOf(collection.find(Filters.regex(NAME_FIELD, Pattern.compile(expression))));
    }

}
