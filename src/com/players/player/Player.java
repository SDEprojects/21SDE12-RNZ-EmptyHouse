//package com.players.player;
//import com.util.JSON_Handler;
//import org.json.simple.JSONObject;
//
//import java.util.ArrayList;
//
//
//public class Player  {
//    private static Player singleton = null;
//    private String name;
//    private String location;
//    //Will look to refactor this JSONObject
//    private static JSONObject commands = JSON_Handler.getJSON("PlayerCommands.JSON", "json");
//    private ArrayList<String> inventory;
//
//
//    public static Player getInstance (String name, String location, JSONObject commands){
//        if(singleton == null){
//            singleton = new Player(name, location, commands );
//    }
//        return singleton;
//    };
//
//    Player(String name, String location, JSONObject commands) {
//        setName(name);
//        setCommands(commands);
//        setLocation(location);
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public JSONObject getCommands() {
//        if(commands.isEmpty()){
//            System.out.println("No commands available");
//        }
//        return commands;
//    }
//
//    public void setCommands(JSONObject commands) {
//        this.commands = commands;
//    }
//
//    @Override
//    public String toString() {
//        return super.getClass().getSimpleName() + "{" +
//                "name='" + name + '\'' +
//                ", location='" + location + '\'' +
//                ", inventory=" + inventory +
//                //This can be removed once the command list grows
//                ", commands=" + commands +
//                '}';
//    }
//
//    public static void main(String[] args) {
//
//        Player player = getInstance("Bill", "house", commands);
//        System.out.println(player.getCommands());
//    }
//}
