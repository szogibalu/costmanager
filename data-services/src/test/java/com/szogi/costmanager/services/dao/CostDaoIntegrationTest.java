package com.szogi.costmanager.services.dao;


import com.szogi.costmanager.core.dao.EmbeddedMongoDbServer;
import com.szogi.costmanager.services.config.CostManagerServicesTestConfiguration;
import com.szogi.costmanager.services.model.Cost;
import com.szogi.costmanager.services.model.Tag;
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
public class CostDaoIntegrationTest {

    @Autowired
    private CostDao costDao;

    @Autowired
    private TagDao tagDao;

    @Autowired
    private TagRepository tagRepository;

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
        Cost savedCost = costDao.save(TestObjectFactory.testCost());
        Cost loadedCost = costDao.findOne(savedCost.getId());
        assertThat(loadedCost, is(notNullValue()));
        assertThat(loadedCost.getTags(), is(not(empty())));
    }

    @Test
    public void saveWithExistingTag() {
        Tag savedTag = tagRepository.save(TestObjectFactory.testTag());
        Cost savedCost = costDao.save(TestObjectFactory.testCost(savedTag));
        Cost loadedCost = costDao.findOne(savedCost.getId());
        assertThat(loadedCost, is(notNullValue()));
        assertThat(loadedCost.getTags(), is(not(empty())));
    }

    @Test
    public void saveWithoutAnyTag() {
        Cost savedCost = costDao.save(TestObjectFactory.testCostWithoutAnyTag());
        Cost loadedCost = costDao.findOne(savedCost.getId());
        assertThat(loadedCost, is(notNullValue()));
        assertThat(loadedCost.getTags(), is((empty())));
    }

    @Test
    public void findAll() {
        int costNumber = 5;
        for (int i = 0; i < costNumber; i++) {
            costDao.save(TestObjectFactory.testCost());
        }
        assertThat(costDao.findAll().size(), is(costNumber));
    }

    @Before
    public void setUp() throws Exception {
        tagDao.createCollection(true);
        costDao.createCollection(true);
    }

    @After
    public void clear() throws Exception {
        costDao.dropCollection();
        tagDao.dropCollection();
    }
}
