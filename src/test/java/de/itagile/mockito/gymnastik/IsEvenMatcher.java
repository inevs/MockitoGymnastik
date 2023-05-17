package de.itagile.mockito.gymnastik;

import org.mockito.ArgumentMatcher;

public class IsEvenMatcher implements ArgumentMatcher<Integer> {

    @Override
    public boolean matches(Integer argument) {
        return argument % 2 == 0;
    }

    public static IsEvenMatcher isEven() {
        return new IsEvenMatcher();
    }
}
