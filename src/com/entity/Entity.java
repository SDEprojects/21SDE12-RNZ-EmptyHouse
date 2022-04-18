package com.entity;

import com.client.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

//    GamePanel gp;

    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNumber = 1;

    public Rectangle solidArea;
    public boolean collisionOn = false;

//    public Entity(GamePanel gp){
//        int screenX = worldX - gp.player.worldX + gp.player.screenX;
//        int screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
//           worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
//           worldY+ gp.tileSize > gp.player.worldY - gp.player.screenY &&
//                worldY- gp.tileSize < gp.player.worldY + gp.player.screenY){
//            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null)
//        }
//    }

}
