package com.noggy.assistant.cooking.dao.persistence;

import com.google.common.collect.ImmutableList;
import com.noggy.assistant.cooking.dao.model.Dish;
import com.noggy.assistant.cooking.rules.EmbeddedMongoRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.noggy.assistant.cooking.dao.db.MongoConnectionManager;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class MongoDishDaoTest {

    @Rule
    public EmbeddedMongoRule mongoRule = new EmbeddedMongoRule();

    @Mock
    private MongoConnectionManager mongoConnectionManager;

    @InjectMocks
    private MongoDishDao target;

    @Test
    public void testInsertAndGetSingleDish() {
        UUID dishId = UUID.randomUUID();

        Dish dish = Dish.builder()
            .dishId(dishId)
            .ingredients(ImmutableList.of("Ingredient1"))
            .name("Test dish")
            .build();

        target.insertDish(dish);
        Optional<Dish> result = target.getDish(dishId);

        assertTrue(result.isPresent());
        assertEquals(result.get(), dish);
    }

}
