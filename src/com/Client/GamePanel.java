package com.Client;



import com.Entity.Player;
import com.Tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public final int maximumScreenColumns = 16;
    public final int maximumScreenRows = 12;
    public final int screenWidth = tileSize * maximumScreenColumns;
    public final int screenHeight = tileSize * maximumScreenRows;

    // World map settings
    public final int maxWorldColumn = 75;
    public final int maxWorldRow = 75;
    public final int maxWorldWidth = tileSize * maxWorldColumn;
    public final int maxWorldHeight = tileSize * maxWorldRow;

    //Frames Per Second
    int FPS = 60;

    TileManager tileM = new TileManager(this);

    // Instantiate the key handler
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
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
        player.update();

    }

    // Drawing the panel with g
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();
    }


}
