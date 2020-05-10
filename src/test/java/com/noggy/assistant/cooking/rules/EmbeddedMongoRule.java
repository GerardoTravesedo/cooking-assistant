package com.noggy.assistant.cooking.rules;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.io.IOException;

public class EmbeddedMongoRule implements TestRule {

    private MongodExecutable mongo;

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                try {
                    startMongo();
                    base.evaluate();
                } finally {
                    stopMongo();
                }
            }
        };
    }

    private void startMongo() throws IOException {
        String ip = "localhost";
        int port = 27017;

        IMongodConfig mongodConfig = new MongodConfigBuilder().version(Version.Main.PRODUCTION)
                .net(new Net(ip, port, Network.localhostIsIPv6()))
                .build();

        MongodStarter starter = MongodStarter.getDefaultInstance();
        mongo = starter.prepare(mongodConfig);
        mongo.start();
    }

    private void stopMongo() {
        mongo.stop();
    }

}
