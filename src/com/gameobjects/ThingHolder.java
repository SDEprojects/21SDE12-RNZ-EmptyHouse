package com.gameobjects;

import com.util.ThingAndThingHolder;

public class ThingHolder extends Thing implements java.io.Serializable {

    private ThingList things;
    private ThingAndThingHolder t_and_th = null;
    private String thingStr = "";

    public ThingHolder(String aName, String aDescription, ThingList tl,
                       ThingHolder aContainer, String description) {
        super(aName, aDescription, description);
        things = tl;
    }

    public ThingHolder(String aName, String aDescription, boolean canTake,
                       boolean canMove, ThingList tl,
                       ThingHolder aContainer) {
        super(aName, aDescription, canTake, canMove, aContainer);
        things = tl;
    }


    public static ContainerThing toContainerThing(Thing t) {
        ContainerThing ct = null;

        if (t instanceof ContainerThing) {
            ct = (ContainerThing) t;
        }
        return ct;
    }

    public int numberOfThings(){
        return things.size();
    }

    private void findThingInAnyList(ThingHolder th, String obname) {
        boolean found = false;
        ContainerThing container;

        for (Thing t : th.getThings()) {
            if (t.getName().equals(obname)) {
                t_and_th = new ThingAndThingHolder(t, th);
                found = true;
            }
            if (!found) {
                container = toContainerThing(t);
                if ((container != null) && (container.isOpen())){
                    findThingInAnyList(container, obname);
                }
            }
        }
    }

    private void doDescribeThings(ThingHolder th) {
        ThingList tlist = th.getThings();
        ContainerThing container;

        for (Thing t : tlist) {
            String containerName = "";
            if (t.getContainer() instanceof ContainerThing) {
                containerName = " [ in " + t.getContainer().getName() + " ]";
            }
            thingStr += t.getName() + containerName + "\n";
            container = toContainerThing(t);
            if ((container != null) && (container.isOpen())) {
                if (container.numberOfThings() > 0) {
                    doDescribeThings(container);
                }
            }
        }
    }

    public String describeThings() {
        thingStr = "";
        doDescribeThings(this);
        return thingStr;
    }

    public boolean containsThing(Thing t) {
        return getThings().contains(t);
    }

    public ThingAndThingHolder findThing(String obname) {
        t_and_th = null;
        findThingInAnyList(this, obname);
        return t_and_th;
    }

    public ThingList getThings() {
        return things;
    }

    public void setThings(ThingList things) {
        this.things = things;
    }

    public void remove(Thing t) {
        things.remove(t);
    }

    public void add(Thing t) {
        things.add(t);
    }

    protected void transferOb(Thing t, ThingHolder fromTH, ThingHolder toTH) {
        fromTH.remove(t);
        toTH.add(t);
        t.setContainer(toTH);
    }

}