package com.House;


import com.gameobjects.Thing;
import com.gameobjects.ThingHolder;
import com.gameobjects.ThingList;

public class Room extends ThingHolder implements java.io.Serializable {

    private Room north, south, west, east, up, down;

    public Room() {
        super("New Room", "", null, null, ""); // init superclass
        this.north = null;
        this.south = null;
        this.west = null;
        this.east = null;
        this.up = null;
        this.down = null;
    }

    public void init(String aName, String aDescription,
                     Room aN, Room aS, Room aW, Room aE, Room anUp, Room aDown,
                     ThingList tl) {
        setName(aName);
        setDescription(aDescription);
        this.north = aN;
        this.south = aS;
        this.west = aW;
        this.east = aE;
        this.up = anUp;
        this.down = aDown;
        setThings(tl);
    }

    // --- accessor methods ---
    // n
    public Room getN() {
        return north;
    }

    public void setN(Room aN) {
        this.north = aN;
    }

    // s
    public Room getS() {
        return south;
    }

    public void setS(Room aS) {
        this.south = aS;
    }

    // e
    public Room getE() {
        return east;
    }

    public void setE(Room aE) {
        this.east = aE;
    }

    // w
    public Room getW() {
        return west;
    }

    void setW(Room aW) {
        this.west = aW;
    }

    public Room getUp() {
        return up;
    }

    public void setUp(Room up) {
        this.up = up;
    }

    public Room getDown() {
        return down;
    }

    public void setDown(Room down) {
        this.down = down;
    }

    public String describe() {
        String roomdesc; // Describes the room player is in
        String thingsdesc; // tells what's in the room

        roomdesc = String.format("%s. %s.", getName(), getDescription());
        thingsdesc = describeThings();
        if (!thingsdesc.isEmpty()) {
            roomdesc += "\nThings here:\n" + thingsdesc;
        }
        return roomdesc;
    }
}