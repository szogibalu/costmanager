package com.szogi.costmanager.services.repository;


import com.szogi.costmanager.core.mongo.EmbeddedMongoDbServer;
import com.szogi.costmanager.core.mongo.MongoDbHelper;
import com.szogi.costmanager.services.config.CostManagerServicesTestConfiguration;
import com.szogi.costmanager.services.model.CostObject;
import com.szogi.costmanager.services.model.TagObject;
import com.szogi.costmanager.services.service.CostObjectService;
import com.szogi.costmanager.services.util.TestObjectFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.szogi.costmanager.core.mongo.MongoDbHelper.CreateOption.DROP_EXISTING;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CostManagerServicesTestConfiguration.class})
public class CostObjectServiceIntegrationTest {

    @Autowired
    private CostObjectService costObjectService;

    @Autowired
    private TagObjectRepository tagObjectRepository;

    @Autowired
    private MongoDbHelper mongoDbHelper;

    @BeforeClass
    public static void init() throws Exception {
        EmbeddedMongoDbServer.start();
    }

    @AfterClass
    public static void close() throws Exception {
        EmbeddedMongoDbServer.stop();
    }

    @Test
    public void saveWithNewTag() {
        CostObject savedCostObject = costObjectService.save(TestObjectFactory.testCost());
        CostObject loadedCostObject = costObjectService.findOne(savedCostObject.getId());
        assertThat(loadedCostObject, is(notNullValue()));
        assertThat(loadedCostObject.getTagObjects(), is(not(empty())));
    }

    @Test
    public void saveWithExistingTag() {
        TagObject savedTagObject = tagObjectRepository.save(TestObjectFactory.testTag());
        CostObject savedCostObject = costObjectService.save(TestObjectFactory.testCost(savedTagObject));
        CostObject loadedCostObject = costObjectService.findOne(savedCostObject.getId());
        assertThat(loadedCostObject, is(notNullValue()));
        assertThat(loadedCostObject.getTagObjects(), is(not(empty())));
    }

    @Test
    public void saveWithoutAnyTag() {
        CostObject savedCostObject = costObjectService.save(TestObjectFactory.testCostWithoutAnyTag());
        CostObject loadedCostObject = costObjectService.findOne(savedCostObject.getId());
        assertThat(loadedCostObject, is(notNullValue()));
        assertThat(loadedCostObject.getTagObjects(), is((empty())));
    }

    @Test
    public void findAll() {
        int costNumber = 5;
        for (int i = 0; i < costNumber; i++) {
            costObjectService.save(TestObjectFactory.testCost());
        }
        assertThat(costObjectService.findAll().size(), is(costNumber));
    }

    @Before
    public void setUp() throws Exception {
        mongoDbHelper.createCollection(TagObject.class, DROP_EXISTING);
        mongoDbHelper.createCollection(CostObject.class, DROP_EXISTING);
    }

    @After
    public void clear() throws Exception {
        mongoDbHelper.dropCollection(CostObject.class);
        mongoDbHelper.dropCollection(TagObject.class);
    }
}
