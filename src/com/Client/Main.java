package com.Client;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.Client.GUI.GUI2;

public class Main {

    static Game game;
    static BackgroundMusic bg = new BackgroundMusic();

    private static void saveGame() { // writes to Ehouse.sav
        try {
            FileOutputStream fos = new FileOutputStream("Ehouse.sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(game); // game
            oos.flush(); // write out any buffered bytes
            oos.close();
            System.out.print("Game saved\n");
        } catch (Exception e) {
            System.out.print("Serialization Error! Can't save data.\n"
                    + e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    private static void loadGame() {
        try {
            FileInputStream fis = new FileInputStream("Ehouse.sav"); // load from Ehouse.sav
            ObjectInputStream ois = new ObjectInputStream(fis);
            game = (Game) ois.readObject();
            bg.playSound();  //play music
            ois.close();
            System.out.print("\n---Game loaded---\n");
        } catch (Exception e) {
            System.out.print("Serialization Error! Can't load data.\n");
            System.out.print(e.getClass() + ": " + e.getMessage() + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        GUI2 i = new GUI2();
//        BufferedReader in;
//        String input;
//        String output;
//// correcting previous commit
//
//        game = new Game();
//        in = new BufferedReader(new InputStreamReader(System.in));
//        game.showIntro();
//        do {
//            System.out.print("Enter your command > ");
//            input = in.readLine();
//            output = "";
//            switch (input) {
//                case "save":
//                    saveGame();
//                    break;
//                case "load":
//                    loadGame();
//                    break;
//                default:
//                    output = game.runCommand(input);
//                    break;
//            }
//            System.out.println(output);
//        } while (!"quit".equals(input));
    }
}



