package com.szogi.costmanager.core.test;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.Command;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.*;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.config.IRuntimeConfig;
import de.flapdoodle.embed.process.extract.ITempNaming;
import de.flapdoodle.embed.process.extract.UUIDTempNaming;
import de.flapdoodle.embed.process.io.directories.FixedPath;
import de.flapdoodle.embed.process.io.directories.IDirectory;
import de.flapdoodle.embed.process.runtime.Network;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfiguration.class})
public class SpringIntegrationTest {

    private static final String TEST_MONGODB_HOST = "localhost";
    private static final int TEST_MONGODB_PORT = 12345;
    private static final String TEST_MONGODB_DB = "test_cm_db";
    private static final String TEST_USER = "test_user";
    private static final String PASSWORD = "cm_test";
    private static MongodExecutable executable;
    private static MongodProcess process;
    @Autowired
    private ApplicationContext applicationContext;

    @BeforeClass
    public static void init() throws IOException {

        IDirectory artifactStorePath = new FixedPath("target/embeddedMongoDb");

        ITempNaming executableNaming = new UUIDTempNaming();

        Command command = Command.MongoD;

        IRuntimeConfig runtimeConfig = new RuntimeConfigBuilder()
                .defaults(command)
                .artifactStore(new ArtifactStoreBuilder()
                        .defaults(command)
                        .download(new DownloadConfigBuilder()
                                .defaultsForCommand(command)
                                .artifactStorePath(artifactStorePath))
                        .executableNaming(executableNaming))
                .build();

        executable = MongodStarter.getInstance(runtimeConfig).prepare(new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(12345, Network.localhostIsIPv6()))
                .build());
        process = executable.start();

        MongoClient client = new MongoClient(TEST_MONGODB_HOST, TEST_MONGODB_PORT);

        DB db = client.getDB(TEST_MONGODB_DB);
        db.addUser(TEST_USER, PASSWORD.toCharArray());
    }

    @AfterClass
    public static void close() throws Exception {
        process.stop();
        executable.stop();
    }

    @Test
    public void contextLoaded() {
        assertThat(applicationContext, is(notNullValue()));
    }
}
