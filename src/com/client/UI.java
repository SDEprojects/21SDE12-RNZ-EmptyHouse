package com.client;

import com.entity.Player;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font font1;
    public int menuOptionNumber = 0;
    Player player;


    public UI(GamePanel gp, Player player){
        this.gp = gp;
        this.player = player;
        font1 = new Font("Serif", Font.BOLD, 100);
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(font1);
        g2.setColor(Color.white);

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        if (gp.gameState == gp.playState){
            drawHUD();
        }
        if (gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

        if(gp.gameState == gp.winState) {
            drawWinScreen();
        }

        if(gp.gameState == gp.loseState) {
            drawLoseScreen();
        }

    }

    public void drawTitleScreen(){
        String text = "Empty House V2";
        int x = getXForCenteredText(text);
        int y = gp.tileSize*2;
        g2.drawString(text, x, y);

        //Menu
        g2.setFont(g2.getFont().deriveFont(font1.BOLD,48F));

        text = "New Game";
        x = getXForCenteredText(text);
        y += gp.tileSize*6;
        g2.drawString(text, x,y);
        if(menuOptionNumber == 0){
            g2.drawString(">", x-gp.tileSize, y);
        }

        text = "Quit";
        x = getXForCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x,y);
        if(menuOptionNumber == 1){
            g2.drawString(">", x-gp.tileSize, y);
        }
    }

    public void drawWinScreen() {
        g2.setFont(g2.getFont().deriveFont(font1.BOLD,72F));
        String text = "You Win!!!";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 2;
        g2.drawString(text, x, y);
    }

    public void drawLoseScreen() {
        g2.setFont(g2.getFont().deriveFont(font1.BOLD,72F));
        String text = "GAME OVER";
        int x = getXForCenteredText(text);
        int y = gp.tileSize * 2;
        g2.drawString(text, x, y);
    }


    public void drawPauseScreen(){
        String text = "PAUSED";
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);

    }

    public void drawHUD(){
        // HUD dimensions
        int x = 0;
        int y = 0;
        int o = 0;
        int p = 0;
        int width = gp.screenWidth;
        int height = gp.tileSize*2;


        // Draw HUD
        g2.setColor(Color.black);
        g2.fillRect(x,y,width, height);

        // Draw HP Counter
        g2.setFont(g2.getFont().deriveFont(font1.BOLD,36F));
        g2.setColor(Color.white);
        int playerHP = 100;
        String HPText = String.valueOf(player.HP);
        x = gp.tileSize*12;
        y = gp.tileSize;
        g2.drawString(HPText, x,y);
        String HpActText = "HP:";
        o = gp.tileSize*10;
        p = gp.tileSize;
        g2.drawString(HpActText, o, p);

//        Instructions
        g2.setFont(g2.getFont().deriveFont(font1.BOLD,24F));
        g2.setColor(Color.white);
        String objective = player.objective;
        x = 300;
        y = 72;
        g2.drawString(objective, (gp.tileSize*10),y);


        // Draw Timer
        g2.setFont(g2.getFont().deriveFont(font1.BOLD,36F));
        g2.setColor(Color.white);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                player.setCurrentTimeLeft(player.getCurrentTimeLeft() -1);
                try {
                    Thread.sleep(100000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1000L,1000000000000000000L);


        String TimerText = String.valueOf((player.getCurrentTimeLeft())/100);
        checkLost();
        x = gp.tileSize;
        y = gp.tileSize;
        g2.drawString(TimerText, x,y);

        // Draw Current Room
        g2.setFont(g2.getFont().deriveFont(font1.BOLD,24F));
        g2.setColor(Color.white);
        String currentRoomText = player.currentRoom;
        g2.drawString(currentRoomText, gp.tileSize, 72);
    }

    public void checkLost() {
        if(player.getCurrentTimeLeft() <=0 || player.getHP() <= 0) {
            gp.gameState = gp.loseState;
        }

    }


    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }


}
