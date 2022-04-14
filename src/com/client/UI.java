package com.client;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font font1;
    public int menuOptionNumber = 0;

    public UI(GamePanel gp){
        this.gp = gp;
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
        int width = gp.screenWidth;
        int height = gp.tileSize*2;


        // Draw HUD
        g2.setColor(Color.black);
        g2.fillRect(x,y,width, height);

        // Draw HP Counter
        g2.setFont(g2.getFont().deriveFont(font1.BOLD,36F));
        g2.setColor(Color.white);
        String HPText = "HP: 100";
        x = gp.tileSize*12;
        y = gp.tileSize;
        g2.drawString(HPText, x,y);

        // Draw Timer
        g2.setFont(g2.getFont().deriveFont(font1.BOLD,36F));
        g2.setColor(Color.white);

        String TimerText = String.valueOf(System.currentTimeMillis()/1000);

        x = gp.tileSize;
        y = gp.tileSize;
        g2.drawString(TimerText, x,y);

    }

    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }


}
