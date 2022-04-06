package com.Client.GUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GUI2 extends JFrame {


        JFrame frame;
        JLabel displayField;
        JLabel displayField2;
        ImageIcon image;
        ImageIcon image2;

        public GUI2() {
            frame = new JFrame("Image Display Test");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            System.out.println(getFileFromResourceAsStream("com/Resources/images/First-Character.png"));
            try {
                image = new ImageIcon(getFileFromResourceAsStream("com/Resources/images/First-Character.png"));
                image2 = new ImageIcon(getFileFromResourceAsStream("com/Resources/images/tilemap.png"));
//
                displayField = new JLabel(image);
                displayField2 = new JLabel(image2);
                frame.add(displayField);
                frame.add(displayField2);
            } catch (Exception e) {
                System.out.println("Image can not be found!");
            }
            frame.setSize(400, 400);
            frame.setVisible(true);
        }

        private static URL getFileFromResourceAsStream(String fileName) {
        ClassLoader classLoader = GUI2.class.getClassLoader();
        URL inputStream = classLoader.getResource(fileName);
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {
            return inputStream;
        }
    }
    }


