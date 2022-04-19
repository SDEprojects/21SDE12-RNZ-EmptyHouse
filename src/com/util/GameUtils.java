package com.util;

import com.gameobjects.Furniture;
import com.gameobjects.Thing;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;

public class GameUtils {
    static ArrayList<Furniture> furniture;
    public static ArrayList<Furniture> setKeyFurniture() {

        Thing key = new Thing("key", "Key to the exit.");
            try {
                furniture = new ArrayList<>(JSON_Handler.getFurniture());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        Furniture keyfurniture = furniture.get(getRandomNumber(0,furniture.size()));
        keyfurniture.setKey(key);
            return furniture;


    }


    public GameUtils() throws IOException, ParseException {
    }

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }



}
