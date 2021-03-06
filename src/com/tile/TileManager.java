package com.tile;

import com.client.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldColumn][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/worldMap.txt");
    }

    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/floor.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/greenwall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stairs.png"));


            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stairs-l.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stairs-r.png"));



        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int column = 0;
            int row = 0;
            while (column < gp.maxWorldColumn && row < gp.maxWorldRow){
                String line = br.readLine();
                while (column < gp.maxWorldColumn){
                    String numbers[] = line.split(" ");
                    int number = Integer.parseInt(numbers[column]);
                    mapTileNum[column][row] = number;
                    column++;
                }
                if (column == gp.maxWorldColumn){
                    column = 0;
                    row++;
                }
            }
            br.close();
        }catch (Exception e){}
    }

    public void draw(Graphics2D g2){
        int worldColumn = 0;
        int worldRow = 0;


        while (worldColumn < gp.maxWorldColumn && worldRow < gp.maxWorldRow){

            int tileNumber = mapTileNum[worldColumn][worldRow];

            int worldX = worldColumn * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;



            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNumber].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldColumn++;

            if(worldColumn == gp.maxWorldColumn){
                worldColumn = 0;
                worldRow++;

            }
        }
    }


}
