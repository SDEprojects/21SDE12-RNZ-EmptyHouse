package com.entity;

import com.client.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;


public class Ghost extends Entity{
    GamePanel gp;

    //public final int screenX;
    //public final int screenY;
    public Ghost(GamePanel gp) {
        this.gp = gp;

        //screenX = gp.screenWidth/2 - (gp.tileSize/2);
        //screenY = gp.screenHeight/2 - (gp.tileSize/2);

        //solidArea = new Rectangle(8,16,20,24);

        setDefaultValues();
        getGhostImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 38;
        worldY = gp.tileSize * 21;
        speed = 3;
        direction = "left";

    }
    public void getGhostImage(){
        try{


            up1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setGhost(){

        int i = getRandomNumber(0, 100);

        if(i <= 25){
            direction = "up";
        }
        if(i > 25 && i <= 50){
            direction = "down";
        }
        if(i > 50 && i <= 75){
            direction = "left";
        }
        if(i > 75 && i <= 100){
            direction = "right";
        }


        spriteCounter++;
        if(spriteCounter > 12) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
            System.out.println("GhostX is " + (worldX/48) +" GhostY is "+(worldY/48));
        }



        }
    public void draw(Graphics2D g2){
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;



        switch (direction){
            case "up":
                if(spriteNumber == 1){image = up1;}
                if(spriteNumber == 2){image = up2;}
                break;
            case "down":
                if(spriteNumber == 1){image = down1;}
                if(spriteNumber == 2){image = down2;}
                break;
            case "left":
                if(spriteNumber == 1){image = left1;}
                if(spriteNumber == 2){image = left2;}
                break;
            case "right":
                if(spriteNumber == 1){image = right1;}
                if(spriteNumber == 2){image = right2;}
                break;
        }

        g2.drawImage(image, gp.tileSize, gp.tileSize, null);

    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

