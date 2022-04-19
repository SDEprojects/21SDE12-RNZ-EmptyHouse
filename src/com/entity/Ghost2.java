package com.entity;

import com.client.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Ghost2 extends Entity{
    GamePanel gp;

    BufferedImage up1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.jpg"));
    BufferedImage up2 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.jpg"));
    BufferedImage down1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.jpg"));
    BufferedImage down2 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.jpg"));
    BufferedImage left1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.jpg"));
    BufferedImage left2 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.jpg"));
    BufferedImage right1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.jpg"));
    BufferedImage right2= ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.jpg"));


    public final int screenX;
    public final int screenY;
    public Ghost2(GamePanel gp) throws IOException {
        this.gp = gp;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle(8,16,20,24);

        setDefaultValues();
//        getGhostImage();
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 22;
        worldY = gp.tileSize * 12;
        speed = 3;
        direction = "up";

    }
//    public void getGhostImage(){
//        System.out.println("called");
//        try{
//
//
//            up1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.jpg"));
//            up2 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.jpg"));
//            down1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.jpg"));
//            down2 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.jpg"));
//            left1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.jpg"));
//            left2 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.jpg"));
//            right1 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost1_1_16x16.jpg"));
//            right2 = ImageIO.read(getClass().getResourceAsStream("/ghostpictures/Ghost2_1_16x16.jpg"));
//            System.out.println("image found");
//
//        }catch (IOException e){
//            e.printStackTrace();
//            System.out.println("image not found");
//        }
//    }

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


        spriteCounter++;
        if(spriteCounter > 12) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCounter = 0;
            //System.out.println("GhostX is " + (worldX/48) +" GhostY is "+(worldY/48));

        }



    }
    public void draw(Graphics2D g2) {
//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;


        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = this.up1;
                }
                if (spriteNumber == 2) {
                    image = this.up2;
                }
                break;
            case "down":
                if (spriteNumber == 1) {
                    image = this.down1;
                }
                if (spriteNumber == 2) {
                    image = this.down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = this.left1;
                }
                if (spriteNumber == 2) {
                    image = this.left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = this.right1;
                }
                if (spriteNumber == 2) {
                    image = this.right2;
                }
                break;

        }


        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        if(screenX - gp.player.screenX == 1 || screenX - gp.player.screenX == 0 && screenY - gp.player.screenY == 1 || screenY - gp.player.screenY == 0 ){
            int hp = Player.HP;
            hp = hp -10;

            Player.HP = hp;
            //System.out.println("YOU LOST HP");
        }

        if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY+ gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY- gp.tileSize < gp.player.worldY + gp.player.screenY){
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        }

    }
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

