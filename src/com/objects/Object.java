package com.objects;

import com.client.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Object {
    public BufferedImage image;
    public boolean collision = false;
    public int worldX, worldY;
    public String name;

    public void draw(Graphics2D g2, GamePanel gp){
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

    }

    public Object(){
        name = "item1";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/furniture/black-couch_16x16.png"));
        }catch (IOException e){
            e.printStackTrace();}

    }


}
