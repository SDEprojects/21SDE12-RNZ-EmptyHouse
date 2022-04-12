package com.gameobjects;

public class ContainerThing extends ThingHolder implements java.io.Serializable {

    private boolean openable;
    private boolean isopen;

    //Working on making doors and drawers possible to open
    public ContainerThing(String aName, String aDescription, ThingList tl,
                          ThingHolder aContainer, String description) {
        super(aName, aDescription, tl, aContainer, description);
        openable = false;
        isopen = true;
    }


    public ContainerThing(String aName, String aDescription,
                          boolean canTake, boolean canMove, boolean canOpen, boolean isOpen,
                          ThingList tl, ThingHolder aContainer) {
        super(aName, aDescription, canTake, canMove, tl, aContainer);
        openable = canOpen;
        isopen = isOpen;
    }

    public boolean isOpenable() {
        return openable;
    }

    public void setOpenable(boolean openable) {
        this.openable = openable;
    }

    public boolean isOpen() {
        return isopen;
    }


    @Override
    public String open() {
        String s;

        if (!openable) {
            s = "Can't open " + getName();
        } else {
            if (isopen) {
                s = "The " + getName() + " is already open.";
            } else {
                isopen = true;
                s = "You open the " + getName();
            }
        }
        return s;
    }

    @Override
    public String close() {
        String s;

        if (!openable) {
            s = "Can't close " + getName();
        } else {
            if (isopen) {
                isopen = false;
                s = "You close the " + getName();
            } else {
                s = "The " + getName() + " is already closed.";
            }
        }
        return s;
    }

    @Override
    public String describe() {
        String s;

        s = super.describe();
        if (openable) {
            if (isopen) {
                s += " (open)";
            } else {
                s += " (closed)";
            }
        }
        if (isopen) {
            if (getThings().size() > 0) {
                s += "\nThere is something in it.";
            }
        }
        return s;
    }

}
