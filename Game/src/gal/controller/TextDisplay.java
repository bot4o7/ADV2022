package gal.controller;

import javafx.scene.control.TextArea;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class TextDisplay extends Thread{
    private int delay;

    public TextDisplay(){
        delay = 5;
    }

    public void play(TextArea textArea, String string) throws InterruptedException {
        for(int i=0; i<string.length(); i++){
            textArea.setText(string.substring(0,i));
            sleep(delay);
        }
    }



    public void setDelay(int delay){
        this.delay = delay;
    }

    public void run(){
        try {
            System.out.println("TextDisplay: running.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
