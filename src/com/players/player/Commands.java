package com.players.player;

public enum Commands {
    //This could be a collection in the future. For now, I am hardcoding some sample strings.
    Crouch("Player has the ability to crouch"),
    Walk("Player has the ability to walk"),
    ReachInBag("Player can reach in their bag"),
    Location("Players location is..."),
    Inventory("Players current inventory is...."),
    NextLocation("The players next possible location is");

    final String currentCommand;

    Commands(String s) {
        currentCommand = s;
    }

    String showCommands(){
        return currentCommand;
    }
}
