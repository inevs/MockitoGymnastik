package de.itagile.mockito.gymnastik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class StubTest {

    private List<Integer> list;

    @BeforeEach
    void setUp() {
        list = mock();
    }

    @Test
    void definesReturnValue() {
        when(list.get(0)).thenReturn(42);
        assertThat(list.get(0), equalTo(42));
    }

    @Test
    void ignoresParameter() {
        when(list.get(anyInt())).thenReturn(42);
        assertThat(list.get(100), equalTo(42));
    }

    @Test
    void throwsExceptionWhenCalled() {
        when(list.get(1)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> {
            list.get(1);
        });
    }

    @Test
    void throwsExceptionOnVoidMethod() {
        doThrow(IllegalArgumentException.class).when(list).add(42);

        assertThrows(IllegalArgumentException.class, () -> {
            list.add(42);
        });
    }
}
