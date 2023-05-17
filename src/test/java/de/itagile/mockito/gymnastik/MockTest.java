package de.itagile.mockito.gymnastik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import java.util.List;

import static de.itagile.mockito.gymnastik.matcher.IsEvenMatcher.isEven;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class MockTest {

    List<Integer> list;
    List<Integer> list2;

    @BeforeEach
    public void setUp() {
        list = mock();
        list2 = mock();
    }

    @Test
    void timesUsage() {
        addTwoIntegers(list);
        verify(list, times(2)).add(anyInt());
    }

    @Test
    void atLeastOnceUsage() {
        addTwoIntegers(list);
        verify(list, atLeastOnce()).add(anyInt());
    }

    @Test
    void directlyArgumentUsage() {
        addTwoIntegers(list);
        verify(list, atLeastOnce()).add(1);
    }

    @Test
    void argumentCaptorUsage() {
        addOneInt(list);
        ArgumentCaptor<Integer> intCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(list).add(intCaptor.capture());
        assertThat(intCaptor.getValue(), equalTo(2));
    }

    @Test
    void argumentMatcherUsage() {
        addOneInt(list);
        verify(list).add(argThat(isEven()));
    }

    @Test
    void inOrderUsage() {
        InOrder inOrder = inOrder(list, list2);
        useBothArguments(list, list2);
        inOrder.verify(list).add(anyInt());
        inOrder.verify(list2).add(anyInt());
    }

    private void addTwoIntegers(List<Integer> list) {
        list.add(1);
        list.add(2);
    }

    private void useBothArguments(List<Integer> list, List<Integer> list2) {
        list.add(1);
        list2.add(2);
    }

    private void addOneInt(List<Integer> list) {
        list.add(2);
    }
}
