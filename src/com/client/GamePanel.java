package com.client;



import com.entity.*;
import com.objects.Object;
import com.objects.ObjectManager;
import com.tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    public final int tileSize = 50;
    public final int maximumScreenColumns = 16;
    public final int maximumScreenRows = 12;
    public final int screenWidth = tileSize * maximumScreenColumns;
    public final int screenHeight = tileSize * maximumScreenRows;
    public final int maxWorldColumn = 50;
    public final int maxWorldRow = 39;
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);

    public Ghost ghost0 = new Ghost(this,player,tileSize*37,tileSize*9);
    public Ghost ghost1 = new Ghost(this,player,tileSize*28,tileSize*5);
    public Ghost ghost2 = new Ghost(this,player,tileSize*22,tileSize*12);
    public Ghost ghost3 = new Ghost(this,player,tileSize*10,tileSize*9);
    public Ghost ghost4 = new Ghost(this,player,tileSize *23,tileSize*26);
    public UI ui = new UI(this,player);
    ObjectManager oManager = new ObjectManager(this);

    public ArrayList<Object> obj = oManager.getObjects();

    // Game states
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int winState = 3;
    public final int loseState = 4;


    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame(){
        oManager.setObjects();
        gameState = titleState;

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // This is the main loop for the game
        double drawInterval = 1000/FPS;
        double nextDrawTime = System.currentTimeMillis() + drawInterval;
        while(gameThread != null){
            // 1 Update: update information such as character positions
            update();
            // 2 Draw: draw the screen with the updated information -calls paintComponent()
            repaint();

            try {
                double remainingTime = nextDrawTime - System.currentTimeMillis();
                if(remainingTime < 0){ remainingTime = 0;} // dealing with negative time

                Thread.sleep((long) remainingTime);
                nextDrawTime = nextDrawTime + drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(){

        if(gameState == playState){
            player.update();

            ghost0.setGhost();
            ghost1.setGhost();
            ghost2.setGhost();
            ghost3.setGhost();
            ghost4.setGhost();
        }


    }

    // Drawing the panel with g
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        //Title Screen
        if(gameState == titleState ||gameState == winState||gameState == loseState){
            ui.draw(g2);
        }

        else if (gameState == playState){
            tileM.draw(g2);

            for (int i =0; i< obj.size(); i++){
                if (obj.get(i) != null){
                    obj.get(i).draw(g2,this);
                }
            }

            ghost0.draw(g2);
            ghost1.draw(g2);
            ghost2.draw(g2);
            ghost3.draw(g2);
            ghost4.draw(g2);
            player.draw(g2);
            ui.draw(g2);
        }

        g2.dispose();
    }


}
