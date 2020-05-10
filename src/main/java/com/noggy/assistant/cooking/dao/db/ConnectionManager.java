package com.noggy.assistant.cooking.dao.db;

// T represents the type of the object that will e used to make calls to the DB
public interface ConnectionManager<T> {

    // C represents the class of the object to be stored/retrieved from the DB. It depends on the collection/table
    // being accessed
    <C> T getRunner(String table, Class<C> type);

}
