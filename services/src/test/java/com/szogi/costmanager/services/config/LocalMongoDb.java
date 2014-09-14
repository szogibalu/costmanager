package com.szogi.costmanager.services.config;


import com.mongodb.DB;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.extract.UUIDTempNaming;
import de.flapdoodle.embed.process.io.directories.FixedPath;

import static com.google.common.base.Throwables.propagate;
import static de.flapdoodle.embed.mongo.Command.MongoD;
import static de.flapdoodle.embed.mongo.distribution.Version.Main.PRODUCTION;
import static de.flapdoodle.embed.process.runtime.Network.localhostIsIPv6;
import static java.lang.System.getProperty;

public class LocalMongoDb {

    private static final String TEST_MONGODB_HOST = "localhost";
    private static final int TEST_MONGODB_PORT = 12345;
    private static final String TEST_MONGODB_DB = "test_cm_db";
    private static final String TEST_USER = "test_user";
    private static final String PASSWORD = "cm_test";

    private static MongodExecutable executable;
    private static MongodProcess process;

    public static void start() {
        MongoClient client;
        try {
            executable = MongodStarter.getInstance(getiRuntimeConfig()).prepare(new MongodConfigBuilder()
                    .version(PRODUCTION)
                    .net(new Net(TEST_MONGODB_PORT, localhostIsIPv6()))
                    .build());
            process = executable.start();

            client = new MongoClient(TEST_MONGODB_HOST, TEST_MONGODB_PORT);
        } catch (Exception e) {
            throw propagate(e);
        }

        DB db = client.getDB(TEST_MONGODB_DB);
        db.addUser(TEST_USER, PASSWORD.toCharArray());
    }

    public static void stop() {
        try {
            process.stop();
            executable.stop();
        } finally {
            executable = null;
            process = null;
        }
    }

    private static IRuntimeConfig getiRuntimeConfig() {
        return new RuntimeConfigBuilder()
                .defaults(MongoD)
                .artifactStore(new ArtifactStoreBuilder()
                        .defaults(MongoD)
                        .download(new DownloadConfigBuilder()
                                .defaultsForCommand(MongoD)
                                .artifactStorePath(getArtifactStorePath()))
                        .executableNaming(new UUIDTempNaming()))
                .build();
    }

    private static FixedPath getArtifactStorePath() {
        return new FixedPath(getProperty("java.io.tmpdir"));
    }
}
