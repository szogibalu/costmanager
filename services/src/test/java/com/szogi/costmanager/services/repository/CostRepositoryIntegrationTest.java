package com.szogi.costmanager.services.repository;


import com.szogi.costmanager.services.config.CostManagerServicesTestConfiguration;
import com.szogi.costmanager.services.config.LocalMongoDb;
import com.szogi.costmanager.services.model.Cost;
import com.szogi.costmanager.services.model.Tag;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.szogi.costmanager.services.util.TestObjectFactory.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CostManagerServicesTestConfiguration.class})
public class CostRepositoryIntegrationTest {

    @Autowired
    private CostExtendedRepository costExtendedRepository;

    @Autowired
    private TagExtendedRepository tagExtendedRepository;

    @Autowired
    private TagRepository tagRepository;

    @BeforeClass
    public static void init() throws Exception {
        LocalMongoDb.start();
    }

    @AfterClass
    public static void close() throws Exception {
        LocalMongoDb.stop();
    }

    @Test
    public void saveWithNewTag() {
        Cost savedCost = costExtendedRepository.save(testCost());
        Cost loadedCost = costExtendedRepository.findOne(savedCost.getId());
        assertThat(loadedCost, is(notNullValue()));
        assertThat(loadedCost.getTags(), is(not(empty())));
    }
    
    @Test
    public void saveWithExistingTag() {
        Tag savedTag = tagRepository.save(testTag());
        Cost savedCost = costExtendedRepository.save(testCost(savedTag));
        Cost loadedCost = costExtendedRepository.findOne(savedCost.getId());
        assertThat(loadedCost, is(notNullValue()));
        assertThat(loadedCost.getTags(), is(not(empty())));
    }

    @Test
    public void saveWithoutAnyTag(){
        Cost savedCost = costExtendedRepository.save(testCostWithoutAnyTag());
        Cost loadedCost = costExtendedRepository.findOne(savedCost.getId());
        assertThat(loadedCost, is(notNullValue()));
        assertThat(loadedCost.getTags(), is((empty())));
    }

    @Test
    public void findAll(){
        int costNumber = 5;
        for (int i = 0; i < costNumber; i++) {
            costExtendedRepository.save(testCost());
        }
      assertThat(costExtendedRepository.findAll().size(), is(costNumber));
    }

    @Before
    public void setUp() throws Exception {
        tagExtendedRepository.createCollection(true);
        costExtendedRepository.createCollection(true);
    }

    @After
    public void clear() throws Exception {
        costExtendedRepository.dropCollection();
        tagExtendedRepository.dropCollection();
    }
}
