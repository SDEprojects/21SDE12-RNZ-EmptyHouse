package com.objects;

import com.client.GamePanel;


public class ObjectManager {
    GamePanel gp;

    public ObjectManager(GamePanel gp){
        this.gp = gp;

    }

    public void setObject(){
        gp.obj[0] = new Object();
        gp.obj[0].worldX = 40 *gp.tileSize;
        gp.obj[0].worldY = 23 *gp.tileSize;
    }
}
