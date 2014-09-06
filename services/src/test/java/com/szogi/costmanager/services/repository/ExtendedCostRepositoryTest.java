package com.szogi.costmanager.services.repository;

import com.szogi.costmanager.services.model.Cost;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExtendedCostRepositoryTest {

    @Mock
    MongoOperations mockedOperations;
    private ExtendedCostRepository target;

    @Before
    public void setUp() throws Exception {
        target = new ExtendedCostRepository(mockedOperations);
    }

    @Test
    public void createIfNotExists() {
        when(mockedOperations.collectionExists(Cost.class)).thenReturn(FALSE);
        target.createCollection();
        verify(mockedOperations).createCollection(Cost.class);
    }

    @Test
    public void createIfExists() {
        when(mockedOperations.collectionExists(Cost.class)).thenReturn(TRUE);
        target.createCollection();
        verify(mockedOperations, never()).createCollection(Cost.class);
    }

    @Test
    public void dropIfNotExists() {
        when(mockedOperations.collectionExists(Cost.class)).thenReturn(FALSE);
        target.dropCollection();
        verify(mockedOperations, never()).dropCollection(Cost.class);
    }

    @Test
    public void dropfExists() {
        when(mockedOperations.collectionExists(Cost.class)).thenReturn(TRUE);
        target.dropCollection();
        verify(mockedOperations).dropCollection(Cost.class);
    }
}