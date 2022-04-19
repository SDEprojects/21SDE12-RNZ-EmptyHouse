package com.util;

import com.gameobjects.Furniture;
import com.gameobjects.Thing;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;


//This JSON handler will handle all the json paths.
public class JSON_Handler {
    public static Collection<Thing> things;
    public static Collection<Furniture> furnatures;

    static {
        try {
            things = getThings();
            furnatures = getFurniture();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public JSON_Handler() throws IOException, ParseException {
    }

    public static Collection<Thing> getThings() throws IOException, ParseException {
        InputStreamReader isr = new InputStreamReader(getFileFromResourceAsStream("com/Assets/json/items.json"));
        Object objItems = new JSONParser().parse(isr);
        Collection<Object> inventory = new ArrayList<>();
        Collection<Thing> items = new ArrayList<>();
        JSONArray jaItems = (JSONArray) objItems;
        inventory.addAll(jaItems);

        for (Object obItems:
                inventory) {
            JSONObject inventoryItem = (JSONObject) obItems;
            Thing questItem;
            questItem = new Thing((String) inventoryItem.get("name"), (String) inventoryItem.get("location"), (String) inventoryItem.get("description"));
            items.add(questItem);
        }
        return items;
    }

    public static Collection<Furniture>  getFurniture() throws IOException, ParseException{
        InputStreamReader isr = new InputStreamReader(getFileFromResourceAsStream("com/Assets/json/furniture.json"));
        Object objItems = new JSONParser().parse(isr);
        Collection<Object> inventory = new ArrayList<>();
        JSONArray jaItems = (JSONArray) objItems;
        inventory.addAll(jaItems);
        Collection<Furniture> items = new ArrayList<>();

        for (Object obItems:
                inventory) {
            JSONObject inventoryItem = (JSONObject) obItems;
            Furniture questItem;
            questItem = new Furniture((String) inventoryItem.get("name"), (String) inventoryItem.get("url"));
            items.add(questItem);
        }
        return items;
    }


    public static InputStream getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = JSON_Handler.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }

    public static void main(String[] args) {

    }
}
