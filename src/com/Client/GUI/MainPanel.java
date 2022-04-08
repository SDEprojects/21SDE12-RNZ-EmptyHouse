package com.Client.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class MainPanel extends JPanel implements KeyListener {

    JLabel label;
    JLabel door;
    JLabel library;
    ImageIcon icon;

    public MainPanel(){
        setPreferredSize(new Dimension(600,600));
        addKeyListener(this);
        URL doorImage = MyFrame.getFileFromResourceAsStream("com/Resources/images/door_10.jpg");

        ImageIcon libraryEntrance = new ImageIcon(doorImage);
        ImageIcon libraryDoor = new ImageIcon(doorImage);
        door = new JLabel();
        door.setBounds(100, 50, 120, 120);
        door.setIcon(libraryEntrance);
        library = new JLabel();
        library.setIcon(libraryDoor);
        library.setBounds(300,120,120,120);

        URL fileToLoad = MyFrame.getFileFromResourceAsStream("com/Resources/images/First-Character_5_5.jpg");
        icon = new ImageIcon(fileToLoad);


        label = new JLabel();
        label.setBounds(0, 0, 100, 100);
        label.setIcon(icon);
        //label.setBackground(Color.red);
        //label.setOpaque(true);
        setBackground(Color.ORANGE);
        add(label);
        add(door);
        add(library);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //keyTyped = Invoked when a key is typed. Uses KeyChar, char output
        switch(e.getKeyChar()) {
            case 'a': label.setLocation(label.getX()-10, label.getY());
                break;
            case 'w': label.setLocation(label.getX(), label.getY()-10);
                break;
            case 's': label.setLocation(label.getX(), label.getY()+10);
                break;
            case 'd': label.setLocation(label.getX()+10, label.getY());
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //keyPressed = Invoked when a physical key is pressed down. Uses KeyCode, int output
        switch(e.getKeyCode()) {
            case 37: label.setLocation(label.getX()-10, label.getY());
                break;
            case 38: label.setLocation(label.getX(), label.getY()-10);
                break;
            case 39: label.setLocation(label.getX()+10, label.getY());
                break;
            case 40: label.setLocation(label.getX(), label.getY()+10);
                break;
        }
    }

    private static  void enterDoor(JLabel character, JLabel fromLabel, JLabel toLabel) {
        if(character.getX() == fromLabel.getX() && character.getY() == fromLabel.getY()) {
            character.setLocation(toLabel.getX() + 3,toLabel.getY() + 3);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        //keyReleased = called whenever a button is released
        System.out.println("You released key char: " + e.getKeyChar());
        System.out.println("You released key code: " + e.getKeyCode());
        if(label.getX() == door.getX() && label.getY() == door.getY()) {
            enterDoor(label,door,library);
            keyReleased(e);

        }
        else if(label.getX() == library.getX() && label.getY() == library.getY()){
            enterDoor(label,library,door);
            keyReleased(e);
        }


    }








}