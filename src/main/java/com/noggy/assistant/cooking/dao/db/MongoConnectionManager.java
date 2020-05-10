package com.noggy.assistant.cooking.dao.db;

import com.mongodb.client.MongoCollection;
import org.mongojack.JacksonMongoCollection;
import org.springframework.stereotype.Component;

@Component
public class MongoConnectionManager implements ConnectionManager<MongoCollection> {

    private static final String DATABASE = "cooking_assistant";

    private MongoClient client = MongoClient.INSTANCE;

    @Override
    public <T> MongoCollection<T> getRunner(String table, Class<T> type) {
        return JacksonMongoCollection.builder()
                .build(client.getClient().getDatabase(DATABASE), type);
    }

}
