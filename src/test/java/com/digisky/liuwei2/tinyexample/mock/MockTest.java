package com.digisky.liuwei2.tinyexample.mock;

import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.exceptions.verification.NoInteractionsWanted;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author liuwei2
 */
public class MockTest {
    @Test
    public void testList() {
        List mockedList = mock(List.class);
        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();
    }

    @Test
    public void testLinkList() {
        LinkedList<String> linkedListMock = mock(LinkedList.class);
        when(linkedListMock.get(0)).thenReturn("VV");
        when(linkedListMock.get(1)).thenReturn("YY");

        assertEquals(linkedListMock.get(0), "VV");
        assertEquals(linkedListMock.get(1), "YY");

        verify(linkedListMock).get(0);
    }

    @Test
    public void testArgMatch() {
        List mockedList = mock(List.class);
        when(mockedList.get(anyInt())).thenReturn("element");
        when(mockedList.contains(argThat(new ArgumentMatcher<Object>() {
            @Override public boolean matches(Object argument) {
                return argument == "VV";
            }
        }))).thenReturn(true);

        assertEquals(mockedList.get(999), "element");
        assertTrue(mockedList.contains("VV"));
        assertFalse(mockedList.contains("YY"));

        verify(mockedList).get(anyInt());
    }

    @Test
    public void testStubbing() {
        List<String> mockedList = mock(List.class);

        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        verify(mockedList).add("once");
        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        verify(mockedList, never()).add("never happened");

        verify(mockedList, atLeastOnce()).add("three times");
        verify(mockedList, atLeast(2)).add("three times");
        verify(mockedList, atMost(5)).add("three times");
    }

    @Test(expected = RuntimeException.class)
    public void testException() {
        List<String> mockedList = mock(List.class);
        doThrow(new RuntimeException()).when(mockedList).clear();
        mockedList.clear();
    }

    @Test
    public void testSequence() {
        List<String> singleMock = mock(List.class);

        singleMock.add("was added first");
        singleMock.add("was added second");

        InOrder inOrder = inOrder(singleMock);
        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        List<String> firstMock = mock(List.class);
        List<String> secondMock = mock(List.class);

        firstMock.add("was called first");
        secondMock.add("was called second");
        InOrder inOrder1 = inOrder(firstMock, secondMock);
        inOrder1.verify(firstMock).add("was called first");
        inOrder1.verify(secondMock).add("was called second");
    }

    @Test
    public void testInteractions() {
        List<String> mockOne = mock(List.class);
        mockOne.add("One");
        verify(mockOne).add("One");
        verify(mockOne, never()).add("Two");

        List<String> mockTwo = mock(List.class);

        verifyZeroInteractions(mockOne);
        verifyZeroInteractions(mockTwo);
    }


    @Test(expected = NoInteractionsWanted.class)
    public void testMoreInteractions() {
        List<String> mockOne = mock(List.class);
        mockOne.add("1");
        mockOne.add("2");

        verify(mockOne).add("1");
        verifyNoMoreInteractions(mockOne);
    }




}
