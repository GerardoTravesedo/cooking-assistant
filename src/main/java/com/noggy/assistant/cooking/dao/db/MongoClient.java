package com.noggy.assistant.cooking.dao.db;

public enum MongoClient {

    // We only want one instance of MongoClient per JVM
    INSTANCE;

    // TODO: Instead of hardcoding the host and port, take them from a property provider (spring props)
    private static final String HOST = "localhost";

    private static final int PORT = 27017;

    private com.mongodb.MongoClient client;

    MongoClient() {
        // Every operation executed against this client will request a new db connection from a pool. Once the operation
        // is completed, that connection is released. If all connections are taken, it will wait for one to be available
        this.client = new com.mongodb.MongoClient(HOST, PORT);
    }

    public com.mongodb.MongoClient getClient() {
        return this.client;
    }

}
