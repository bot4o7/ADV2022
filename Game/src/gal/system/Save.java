package gal.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.awt.image.BufferedImage;
import java.io.*;

public class Save {
    private int index;
    private String name;
    private String date;
    private Navi current_Navi;

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public Navi getCurrent_Navi() {
        return current_Navi;
    }

    public Save(){
    }
    public Save(int index, String name, String date, Navi current_Navi){
        this.index = index;
        this.name = name;
        this.date = date;
        this.current_Navi = current_Navi;
    }
    public Save(int index) throws IOException {
        load(index);
    }
    public void delete(){
        this.current_Navi = null;
        this.date = null;
        this.name = null;
    }
    public void save() throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder.create();
        String out = gson.toJson(this);
        System.out.println(out);
        File file = new File("src/gal/save/"+index+".savedata");
        if( file.exists() ){
            file.delete();
        }
        file.getParentFile().mkdirs();
        file.createNewFile();
//        FileWriter fileWriter = new FileWriter(file);
//        fileWriter.write(out);
        FileWriter fw = new FileWriter(file);
//        FileOutputStream fOut = new FileOutputStream(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(out);
        bw.flush();
//        System.out.println(file.getAbsolutePath());
//        System.out.println("end1");
    }
    private void load(int index) throws IOException {
        File file = new File("src/gal/save/"+index+".savedata");

        if( file.exists() ){
//            java.lang.System.out.println("yes");
//            java.lang.System.out.println(file.getAbsolutePath());
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String content = bufferedReader.readLine();
//            java.lang.System.out.println("\n\nget Save:\n"+content);
            bufferedReader.close();

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            Save save = gson.fromJson(content, Save.class);
            this.index = save.index;
            this.current_Navi = save.current_Navi;
            this.date = save.date;
            this.name = save.name;
        }else{
//            java.lang.System.out.println("no");
            this.index = index;
            this.current_Navi = null;
            this.date = null;
            this.name = null;
        }

    }
}
