package com.szogi.costmanager.services.dao;


import com.szogi.costmanager.core.dao.EmbeddedMongoDbServer;
import com.szogi.costmanager.services.config.CostManagerServicesTestConfiguration;
import com.szogi.costmanager.services.model.CostObject;
import com.szogi.costmanager.services.model.TagObject;
import com.szogi.costmanager.services.util.TestObjectFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CostManagerServicesTestConfiguration.class})
public class CostObjectDaoIntegrationTest {

    @Autowired
    private CostObjectDao costObjectDao;

    @Autowired
    private TagObjectDao tagObjectDao;

    @Autowired
    private TagObjectRepository tagObjectRepository;

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
        CostObject savedCostObject = costObjectDao.save(TestObjectFactory.testCost());
        CostObject loadedCostObject = costObjectDao.findOne(savedCostObject.getId());
        assertThat(loadedCostObject, is(notNullValue()));
        assertThat(loadedCostObject.getTagObjects(), is(not(empty())));
    }

    @Test
    public void saveWithExistingTag() {
        TagObject savedTagObject = tagObjectRepository.save(TestObjectFactory.testTag());
        CostObject savedCostObject = costObjectDao.save(TestObjectFactory.testCost(savedTagObject));
        CostObject loadedCostObject = costObjectDao.findOne(savedCostObject.getId());
        assertThat(loadedCostObject, is(notNullValue()));
        assertThat(loadedCostObject.getTagObjects(), is(not(empty())));
    }

    @Test
    public void saveWithoutAnyTag() {
        CostObject savedCostObject = costObjectDao.save(TestObjectFactory.testCostWithoutAnyTag());
        CostObject loadedCostObject = costObjectDao.findOne(savedCostObject.getId());
        assertThat(loadedCostObject, is(notNullValue()));
        assertThat(loadedCostObject.getTagObjects(), is((empty())));
    }

    @Test
    public void findAll() {
        int costNumber = 5;
        for (int i = 0; i < costNumber; i++) {
            costObjectDao.save(TestObjectFactory.testCost());
        }
        assertThat(costObjectDao.findAll().size(), is(costNumber));
    }

    @Before
    public void setUp() throws Exception {
        tagObjectDao.createCollection(true);
        costObjectDao.createCollection(true);
    }

    @After
    public void clear() throws Exception {
        costObjectDao.dropCollection();
        tagObjectDao.dropCollection();
    }
}
