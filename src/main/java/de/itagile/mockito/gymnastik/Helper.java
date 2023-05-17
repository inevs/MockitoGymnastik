package de.itagile.mockito.gymnastik;

public class Helper {
    public int computeSomeValues() {
        configureSomeStuff();
        return 42 * getValuesFromNetwork();
    }

    public int configureDatabase() {
        makeSomeBasicSettings();
        return 42;
    }

    void configureSomeStuff() {
    }

    void makeSomeBasicSettings() {
        throw new IllegalArgumentException("I dont have a database");
    }

    int getValuesFromNetwork() {
        throw new IllegalArgumentException("I dont have network");
    }
}
