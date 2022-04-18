package com.entity;

import com.client.GamePanel;
import com.client.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    public String currentRoom;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        currentRoom = "Library";

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8,16,20,24);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 40;
        worldY = gp.tileSize * 23;
        speed = 5;
        direction = "down";
    }

    public void getPlayerImage(){
        try{

            up1 = ImageIO.read(getClass().getResourceAsStream("/player/c-u-3.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/c-u-2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/c-d-3.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/c-d-2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/c-l-3.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/c-l-2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/c-r-3.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/c-r-2.png"));

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed || keyH.downPressed ||
                keyH.leftPressed || keyH.rightPressed){
            if(keyH.upPressed) {
                direction = "up";
            }
            else if(keyH.downPressed) {
                direction = "down";
            }
            else if(keyH.leftPressed) {
                direction = "left";
            }
            else if(keyH.rightPressed) {
                direction = "right";
            }


            // Check if stairs
            if(((gp.tileSize*34) <= worldX && worldX <= (gp.tileSize*36)) &&
                    ((gp.tileSize*31) <= worldY && worldY <= (gp.tileSize*33))){
                worldY += gp.tileSize*7;
            }
            if(((gp.tileSize*34) <= worldX && worldX <= (gp.tileSize*36)) &&
                    ((gp.tileSize*34) <= worldY && worldY <= (gp.tileSize*36))){
                worldY -= gp.tileSize*7;
            }
            if(((gp.tileSize*22) <= worldX && worldX <= (gp.tileSize*24)) &&
                    ((gp.tileSize*18) <= worldY && worldY <= (gp.tileSize*20))) {
                worldY -= gp.tileSize * 7;
                worldX += gp.tileSize * 5;
            }
            if(((gp.tileSize*27) <= worldX && worldX <= (gp.tileSize*29)) &&
                    ((gp.tileSize*15) <= worldY && worldY <= (gp.tileSize*17))){
                worldY += gp.tileSize*7;
                worldX -= gp.tileSize * 5;
            }



            // Current room
            if(((gp.tileSize*32) <= worldX && worldX <= (gp.tileSize*43)) &&
                    ((gp.tileSize*21) <= worldY && worldY <= (gp.tileSize*33))){
                currentRoom = "Library";
            } else if(((gp.tileSize*20) <= worldX && worldX <= (gp.tileSize*31)) &&
                    ((gp.tileSize*21) <= worldY && worldY <= (gp.tileSize*24))){
                        currentRoom = "Dining Room";}
            else {currentRoom = "???";}




            // Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // If collision is false, player can move
            if (collisionOn == false){
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12) {
                if (spriteNumber == 1) {
                    spriteNumber = 2;
                } else if (spriteNumber == 2) {
                    spriteNumber = 1;
                }
                spriteCounter = 0;
                //System.out.println("World X is " + worldX +" World Y is "+worldY);
            }

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


        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }




}
