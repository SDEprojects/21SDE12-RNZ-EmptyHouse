package com.objects;

import com.client.GamePanel;
import com.util.JSON_Handler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Object {
    public BufferedImage image;
    public boolean collision = false;
    public int worldX, worldY;
    public String name;

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Object(String name, String fileName){
        setName(name);
        System.out.println(fileName);
        try{
//            image = ImageIO.read(getClass().getResourceAsStream(fileName));
//            image = ;
            setImage(ImageIO.read(getClass().getResourceAsStream(fileName)));

        }catch (IOException e){
            e.printStackTrace();}

    }


}
