package com.objects;

import com.client.GamePanel;
import com.gameobjects.Furniture;
import com.util.GameUtils;

import java.util.ArrayList;
import java.util.Collection;


public class ObjectManager {
    GamePanel gp;
    public ArrayList<Furniture> items = GameUtils.setKeyFurniture();
    public ArrayList<Object> objects = new ArrayList<>();


    public ArrayList<Object> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<Object> objects) {
        this.objects = objects;
    }

    public void addObject(Object object) {
        this.objects.add(object);
    }

    public ObjectManager(GamePanel gp){
        this.gp = gp;
        for (Furniture item:items
             ) {
            Object obj = new Object(item.getName(), item.getUrl());
            addObject(obj);

        }

    }

    public void setObjects(){
        int[] x = {30, 16, 35, 40, 15, 12};
        int[] y = {30, 40, 20, 6, 10, 4};
        for (int i = 0; i < objects.size(); i++) {
            gp.obj.set(i, objects.get(i));
            int xAxis = x[i];
            int yAxis = y[i];
            gp.obj.get(i).worldX = (xAxis) *gp.tileSize;
            gp.obj.get(i).worldY = (yAxis) *gp.tileSize;
        }

        }

}
