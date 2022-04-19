package com.client;

import javax.sound.sampled.*;
import java.util.Objects;

public class BackgroundMusic{
    public void playSound() {
        new Thread(() -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        Objects.requireNonNull(Main.class.getResourceAsStream("horror.wav")));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }).start();
    }
}
