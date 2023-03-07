package gal.controller;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class Music extends Thread{
    private File f;
    private URI uri;
    private URL url;

    private AudioClip aau;

    private String current_BGM;

    Music(){
        aau = null;
    }

    public boolean had_Played(){
        if( aau == null ){
            return false;
        }else{
            return true;
        }
    }

    public String getCurrent_BGM(){
        return current_BGM;
    }

    public void play_BGM(String filename){
        try {
            current_BGM = filename;
            f = new File("src/gal/data/Audio/BGM/"+filename); // 绝对路径
            uri = f.toURI();
            url = uri.toURL(); // 解析路径
            System.out.println(uri.toString());
            aau = Applet.newAudioClip(url);
            aau.loop(); // 单曲循环
            System.out.println("Playing BGM: "+filename+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        try {

            f = new File("src/dat/music/main.wav"); // 绝对路径
            uri = f.toURI();
            url = uri.toURL(); // 解析路径
            System.out.println(uri.toString());
            aau = Applet.newAudioClip(url);
            aau.loop(); // 单曲循环
            System.out.println("Playing BGM: main.wav\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play_Once(String filename){
        try {
            f = new File("src/gal/data/Audio/SE/"+filename+".wav"); // 绝对路径
            uri = f.toURI();
            url = uri.toURL(); // 解析路径
            aau = Applet.newAudioClip(url);
            aau.play();
            System.out.println("Playing BGM: "+filename+"\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop_Playing(){
        aau.stop();
    }


//    public void run() { // 注意，java只能播放无损音质，如.wav这种格式
//        try {
//
//            f = new File("飞雪玉花.wav"); // 绝对路径
//            uri = f.toURI();
//            url = uri.toURL(); // 解析路径
//            AudioClip aau;
//            aau = Applet.newAudioClip(url);
//            aau.loop(); // 单曲循环
//            System.out.println("");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}