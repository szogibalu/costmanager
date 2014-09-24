package com.szogi.costmanager.core.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;

import javax.annotation.Resource;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MongoDbHelperTest {

    @Mock
    MongoOperations mockedOperations;

    @InjectMocks
    @Resource
    private MongoDbHelper target;

    @Test
    public void createIfNotExists() {
        when(mockedOperations.collectionExists(TestClass.class)).thenReturn(FALSE);
        target.createCollection(TestClass.class);
        verify(mockedOperations).createCollection(TestClass.class);
    }

    @Test
    public void createIfExists() {
        when(mockedOperations.collectionExists(TestClass.class)).thenReturn(TRUE);
        target.createCollection(TestClass.class);
        verify(mockedOperations, never()).createCollection(TestClass.class);
    }

    @Test
    public void dropIfNotExists() {
        when(mockedOperations.collectionExists(TestClass.class)).thenReturn(FALSE);
        target.dropCollection(TestClass.class);
        verify(mockedOperations, never()).dropCollection(TestClass.class);
    }

    @Test
    public void dropfExists() {
        when(mockedOperations.collectionExists(TestClass.class)).thenReturn(TRUE);
        target.dropCollection(TestClass.class);
        verify(mockedOperations).dropCollection(TestClass.class);
    }

    private static class TestClass {
    }
}