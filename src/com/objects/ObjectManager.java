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
//        ArrayList<Object> objs = null;
        for (Furniture item:items
             ) {
            Object obj = new Object(item.getName(), item.getUrl());
            System.out.println(obj.getName());
            addObject(obj);

        }

    }

    public void setObjects(){
        for (int i = 0; i < objects.size(); i++) {
            gp.obj.set(i, objects.get(i));
            gp.obj.get(i).worldX = GameUtils.getRandomNumber(0,50) *gp.tileSize;
            gp.obj.get(i).worldY = GameUtils.getRandomNumber(0,50) *gp.tileSize;
        }

        }
//        gp.obj[0] = new Object();
//        gp.obj[0].worldX = 40 *gp.tileSize;
//        gp.obj[0].worldY = 23 *gp.tileSize;

}
