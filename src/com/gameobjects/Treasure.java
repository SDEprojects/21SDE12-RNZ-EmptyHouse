package com.gameobjects;

public class Treasure extends Thing implements java.io.Serializable{


    private int value;

    public Treasure(String aName, String aDescription, ThingHolder aContainer, String description) {
        super(aName, aDescription, description); // init superclass
        this.value = 0;
    }

    public Treasure(String aName, String aDescription, int aValue,
                    ThingHolder aContainer, String description) {
        super(aName, aDescription, description); // init superclass
        this.value = aValue;
    }

    public int getValue() {
        return value;
    }

}
