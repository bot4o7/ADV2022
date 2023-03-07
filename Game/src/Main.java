import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gal.controller.Game;
import gal.controller.ScriptWriter;
import gal.system.ProjectInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //   --------------写入测试剧本
//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gal.system.Scene scene = new gal.system.Scene();
//
//        scene.testInit();
//        Gson gson = gsonBuilder.create();
//        String out = gson.toJson(scene);
//        System.out.println(out);
//        File file = new File("src/gal/data/Scene/Beginning.scene");
//        if( file.exists() ){
//            file.delete();
//        }
//        file.getParentFile().mkdirs();
//        file.createNewFile();
////        FileWriter fileWriter = new FileWriter(file);
////        fileWriter.write(out);
//        FileWriter fw = new FileWriter(file);
////        FileOutputStream fOut = new FileOutputStream(file);
//        BufferedWriter bw = new BufferedWriter(fw);
//        bw.write(out);
//        bw.flush();
//        System.out.println(file.getAbsolutePath());
//        System.out.println("end1");
//
//
//
//        gsonBuilder = new GsonBuilder();
//        gal.system.ProjectInfo info = new ProjectInfo();
//        info.setName("Project1");
//        info.setDateCreate(ScriptWriter.DATE_FORMAT.format(new Date()));
//        info.setDateEdit(ScriptWriter.DATE_FORMAT.format(new Date()));
//        info.setAuthor("bot4o7");
//        info.setIntro("This is a test intro.\nfuck you, my dear.\nhehe. \n  :)\n");
//
//        gson = gsonBuilder.create();
//        out = gson.toJson(info);
//        System.out.println(out);
//        file = new File("src/gal/data/"+info.getName()+".project_info");
//        if( file.exists() ){
//            file.delete();
//        }
//        file.getParentFile().mkdirs();
//        file.createNewFile();
//        fw = new FileWriter(file);
//        bw = new BufferedWriter(fw);
//        bw.write(out);
//        bw.flush();
//        System.out.println(file.getAbsolutePath());
//        System.out.println("end2");
//
//        file = new File("src/gal/workplace/"+info.getName()+"/"+info.getName()+".project_info");
//        if( file.exists() ){
//            file.delete();
//        }
//        file.getParentFile().mkdirs();
//        file.createNewFile();
//        fw = new FileWriter(file);
//        bw = new BufferedWriter(fw);
//        bw.write(out);
//        bw.flush();
//        System.out.println(file.getAbsolutePath());
//        System.out.println("end2");


//        FileReader fr = new FileReader(file);
//        BufferedReader br = new BufferedReader(fr);
//        String input = null;
//
//        while( (input = br.readLine())!=null ){
//            System.out.println(input+" a line");
//        }
//
//        System.out.println("end2");


        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("gal/view/Game.fxml"));
        Parent root = loader.load();
        gal.controller.Game target = loader.getController();
        target.init_Main(primaryStage);
        target.initGame();

        primaryStage.setTitle("Game");
        primaryStage.setScene(new Scene(root, 1000, 750));
        primaryStage.show();
//        Line line = new Line();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
