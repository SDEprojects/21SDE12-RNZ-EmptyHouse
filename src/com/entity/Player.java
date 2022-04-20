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
    public String objective;
    public boolean keyFound = false;
    public int HP;
    public int currentTimeLeft = 4000;

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getCurrentTimeLeft() {
        return currentTimeLeft;
    }

    public void setCurrentTimeLeft(int currentTimeLeft) {
        this.currentTimeLeft = currentTimeLeft;
    }

    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        currentRoom = "Entrance";

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8,16,20,24);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 31;
        speed = 5;
        direction = "up";
        HP = 100;
        objective = "Find the key!";
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
            if(((gp.tileSize*31) <= worldX && worldX <= (gp.tileSize*43)) &&
                    ((gp.tileSize*19) <= worldY && worldY <= (gp.tileSize*35))){
                currentRoom = "Library";
            } else if(((gp.tileSize*13) <= worldX && worldX <= (gp.tileSize*31)) &&
                    ((gp.tileSize*19) <= worldY && worldY <= (gp.tileSize*24))){
                currentRoom = "Dining Room";}
            else if(((gp.tileSize*6) <= worldX && worldX <= (gp.tileSize*13)) &&
                    ((gp.tileSize*19) <= worldY && worldY <= (gp.tileSize*32))){
                currentRoom = "Kitchen";}
            else if(((gp.tileSize*13) <= worldX && worldX <= (gp.tileSize*31)) &&
                    ((gp.tileSize*24) <= worldY && worldY <= (gp.tileSize*35))){
                currentRoom = "Entrance";}
            else if(((gp.tileSize*6) <= worldX && worldX <= (gp.tileSize*16)) &&
                    ((gp.tileSize*1) <= worldY && worldY <= (gp.tileSize*19))){
                currentRoom = "Master Bedroom";}
            else if(((gp.tileSize*30) <= worldX && worldX <= (gp.tileSize*41)) &&
                    ((gp.tileSize*1) <= worldY && worldY <= (gp.tileSize*16))){
                currentRoom = "Bedroom";}
            else if(((gp.tileSize*16) <= worldX && worldX <= (gp.tileSize*30)) &&
                    ((gp.tileSize*1) <= worldY && worldY <= (gp.tileSize*16))){
                currentRoom = "Second Floor";}
            else {currentRoom = "???";}

            //Check for key
            if(((gp.tileSize*11) <= worldX && worldX <= (gp.tileSize*13)) &&
                    ((gp.tileSize*3) <= worldY && worldY <= (gp.tileSize*5))){
              objective = "Key found! Exit at the entrance.";
              keyFound = true;
            }

            //Win conditions
            if(keyFound &&
                    ((gp.tileSize*23) <= worldX && worldX <= (gp.tileSize*25)) &&
                    ((gp.tileSize*32) <= worldY && worldY <= (gp.tileSize*33))){
                objective = "Key found! Exit at the entrance.";
                gp.gameState = gp.winState;
            }


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
            }
        }
    }

    public void draw(Graphics2D g2){

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
