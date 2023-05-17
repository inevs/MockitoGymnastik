package de.itagile.mockito.gymnastik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class SpyTest {
    private Helper helper;
    private List<Integer> list;

    @BeforeEach
    void setUp() {
        helper = spy(new Helper());
        list = spy(new ArrayList<>());
    }

    @Test
    void mocksOnlyPartOfClass() {
        when(list.size()).thenReturn(42);

        list.add(5);
        list.add(4);

        assertThat(list.get(0), equalTo(5));
        assertThat(list.get(1), equalTo(4));

        assertThat(list.size(), equalTo(42));
    }

    @Test
    void mocksInternalMethodCalls() {
        doReturn(10).when(helper).getValuesFromNetwork();
        assertThat(helper.computeSomeValues(), equalTo(420));
    }

    @Test
    void verifiesInternalMethodCalls() {
        doReturn(10).when(helper).getValuesFromNetwork();
        assertThat(helper.computeSomeValues(), equalTo(420));
        verify(helper).configureSomeStuff();
    }

    @Test
    void imposeMethodsThatShouldNotBeUsedInTest() {
        doNothing().when(helper).makeSomeBasicSettings();
        assertThat(helper.configureDatabase(), equalTo(42));
    }
}
