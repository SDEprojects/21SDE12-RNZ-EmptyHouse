package com.players.npc;

public class Friend {
    private String name = null;

    public Friend(){

    }

    public Friend(String name) {
        setName(name);
    }


//This will get refactored after Meri finishes the player class
    public void talk(){
        System.out.println(getName() + " is talking");
    };

    public void think(){
        System.out.println(getName() + " is thinking");
    };

    public void doAction(){
        System.out.println(getName() + " is doing something....");
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.getClass().getSimpleName() + ": " + getName();
    }
}
