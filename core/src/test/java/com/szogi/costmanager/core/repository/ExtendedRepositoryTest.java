package com.szogi.costmanager.core.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoOperations;

import javax.annotation.Resource;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExtendedRepositoryTest {

    @Mock
    MongoOperations mockedOperations;

    @InjectMocks
    @Resource
    private TestableExtendedRepository target;

    @Test
    public void createIfNotExists() {
        when(mockedOperations.collectionExists(TestClass.class)).thenReturn(FALSE);
        target.createCollection();
        verify(mockedOperations).createCollection(TestClass.class);
    }

    @Test
    public void createIfExists() {
        when(mockedOperations.collectionExists(TestClass.class)).thenReturn(TRUE);
        target.createCollection();
        verify(mockedOperations, never()).createCollection(TestClass.class);
    }

    @Test
    public void dropIfNotExists() {
        when(mockedOperations.collectionExists(TestClass.class)).thenReturn(FALSE);
        target.dropCollection();
        verify(mockedOperations, never()).dropCollection(TestClass.class);
    }

    @Test
    public void dropfExists() {
        when(mockedOperations.collectionExists(TestClass.class)).thenReturn(TRUE);
        target.dropCollection();
        verify(mockedOperations).dropCollection(TestClass.class);
    }

    private static class TestClass{}

    private static class TestableExtendedRepository extends ExtendedRepository{
        @Override
        protected Class<?> getCollection() {
            return TestClass.class;
        }
    }
}