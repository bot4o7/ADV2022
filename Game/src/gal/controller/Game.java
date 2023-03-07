package gal.controller;

import gal.system.Navi;
import gal.system.Save;
import javafx.animation.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneSave;

    @FXML
    private Pane paneLoad;

    @FXML
    private Pane paneMain;

    @FXML
    private ImageView image_BG_Main;

    @FXML
    private Button button_Start;

    @FXML
    private Button button_Load;

    @FXML
    private Button button_Gallery;

    @FXML
    private Button button_Quit;

    @FXML
    private Pane paneGame;


    @FXML
    private ImageView imageBG;


    @FXML
    private ImageView image_Bubble;

    @FXML
    private ImageView image_Char;

    @FXML
    private TextArea textArea;

    @FXML
    private Button button_Save_Game;

    @FXML
    private Button button_Load_Game;

    @FXML
    private Button button_Log_Game;

    @FXML
    private Button button_Hide_Game;

    @FXML
    private Button button_Menu_Game;

    @FXML
    private Rectangle image_Bubble_Speaker;

    @FXML
    private Text text_Speaker;

    @FXML
    private Pane paneGameTouch;

    @FXML
    private Pane paneSavePic;

    @FXML
    private ImageView imageSave1;

    @FXML
    private ImageView imageSave2;

    @FXML
    private ImageView imageSave3;

    @FXML
    private ImageView imageSave4;

    @FXML
    private ImageView imageSave5;

    @FXML
    private ImageView imageSave6;

    @FXML
    private ImageView imageSave7;

    @FXML
    private ImageView imageSave8;

    @FXML
    private ImageView imageSave9;

    @FXML
    private ImageView imageSave10;

    @FXML
    private ImageView imageSave11;

    @FXML
    private ImageView imageSave12;

    @FXML
    private Pane paneSaveInfo;

    @FXML
    private Label labelSaveName;

    @FXML
    private Label labelSaveDate;

    @FXML
    private TextField textFieldSaveDate;

    @FXML
    private TextField textFieldSaveName;

    @FXML
    private Button buttonSaveConfirm;

    @FXML
    private Button button_Write;

    @FXML
    private Button buttonSaveLoad;

    @FXML
    private Button buttonSaveDel;

    @FXML
    private Button buttonSaveCancel;

    @FXML
    private RadioButton choiceSavePage1;

    @FXML
    private RadioButton choiceSavePage2;

    @FXML
    private RadioButton choiceSavePage3;

    @FXML
    private RadioButton choiceSavePage4;

    @FXML
    private RadioButton choiceSavePage5;

    @FXML
    private RadioButton choiceSavePage6;

    @FXML
    private RadioButton choiceSavePage7;

    @FXML
    private RadioButton choiceSavePage8;

    @FXML
    private RadioButton choiceSavePage9;

    @FXML
    private RadioButton choiceSavePage10;

    public Game() throws IOException {
    }

    @FXML
    void click_Button_Gallery(MouseEvent event) {
        if( event.isSecondaryButtonDown() ){

        }
    }
    @FXML
    void click_Button_Write(MouseEvent event) throws IOException {
        player_BGM.stop_Playing();

        Stage writerStage = new Stage();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ScriptWriter.fxml"));
//        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/ScriptWriter.fxml"));
        System.out.println("loader path: \n"+this.getClass());
        Parent root = (Parent)loader.load();

        ScriptWriter target = loader.getController();


        Scene scene = new Scene(root);

        writerStage.setScene(scene);
        target.init();
        writerStage.setTitle("Game");
        writerStage.show();
    }

    @FXML
    void click_Button_Load(MouseEvent event) {

        textFieldSaveName.setEditable(false);

        buttonSaveLoad.setVisible(true);
        buttonSaveConfirm.setVisible(false);

        paneMain.setVisible(false);
        paneGame.setVisible(false);
        paneSave.setVisible(true);

        Save save;
        groupPageSave.selectToggle(choiceSavePage1);
        for(int i=0; i<listImageSave.size(); i++){
            save = listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+i];
            if(save.getDate()!=null){
                listImageSave.get(i).setImage(new Image("file:src/gal/save/"+save.getIndex()+".png"));
            }else{
                listImageSave.get(i).setImage(new Image("file:src/gal/data/Image/System/NoData.png"));
            }
        }
    }

    @FXML
    void click_Button_Quit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void click_Button_Start(MouseEvent event) throws IOException, InterruptedException {
        switchPane(PANE_GAME);
    }


    @FXML
    void click_Hide_Game(MouseEvent event) {
        bubble_Switch();
    }

    @FXML
    void click_Load_Game(MouseEvent event) {
        textFieldSaveName.setEditable(false);

        buttonSaveLoad.setVisible(true);
        buttonSaveConfirm.setVisible(false);

        paneGame.setVisible(false);
        paneSave.setVisible(true);
        paneSavePic.requestFocus();
        Save save;
        groupPageSave.selectToggle(choiceSavePage1);
        for(int i=0; i<listImageSave.size(); i++){
            save = listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+i];
            if(save.getDate()!=null){
                listImageSave.get(i).setImage(new Image("file:src/gal/save/"+save.getIndex()+".png"));
            }else{
                listImageSave.get(i).setImage(new Image("file:src/gal/data/Image/System/NoData.png"));
            }
        }
        textFieldSaveName.setText("");
        textFieldSaveDate.setText("-- / -- / ----    -- : -- : --");
    }

    @FXML
    void click_Log_Game(MouseEvent event) {

    }

    @FXML
    void click_Menu_Game(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to back to menu?", new ButtonType[]{ButtonType.YES,ButtonType.NO});
        alert.showAndWait().ifPresent(response -> {
            if( response == ButtonType.YES ){
                if(buttons != null){
                    for(int i=0; i<buttons.size(); i++){
                        buttons.get(i).getButton().setVisible(false);
                    }
                    paneGame.getChildren().remove(buttons);
                    buttons = null;
                   
                }
                player_BGM.stop_Playing();
                navi = null;
                try {
                    switchPane(PANE_MAIN);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                player_BGM.play_BGM("main.wav");
            }
        });
    }

    @FXML
    void click_Save_Game(MouseEvent event) throws IOException, InterruptedException, AWTException {
//        switchPane(PANE_SAVE);
//        anchorPane.getChildren().remove(paneGame);
        paneGame.setVisible(false);
//        anchorPane.getChildren().add(paneSave);
        paneSave.setVisible(true);
        paneSavePic.requestFocus();
        buttonSaveLoad.setVisible(false);
        buttonSaveConfirm.setVisible(true);

        prtScreen();

        textFieldSaveName.setEditable(true);

        Save save;
        groupPageSave.selectToggle(choiceSavePage1);
        for(int i=0; i<listImageSave.size(); i++){
            save = listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+i];
            if(save.getDate()!=null){
                listImageSave.get(i).setImage(new Image("file:src/gal/save/"+save.getIndex()+".png"));
            }else{
                listImageSave.get(i).setImage(new Image("file:src/gal/data/Image/System/NoData.png"));
//                System.out.println("shit...1"+listImageSave.get(i).toString());
            }
        }
        textFieldSaveName.setText("");
        textFieldSaveDate.setText("-- / -- / ----    -- : -- : --");
    }

    @FXML
    void clickSaveCancel(MouseEvent event) {
        if(navi!=null){
            paneGame.setVisible(true);
            selectedSaveImage = null;
            selectedSave = null;
            paneSave.setVisible(false);
        }else{
            paneMain.setVisible(true);
            selectedSaveImage = null;
            selectedSave = null;
            paneSave.setVisible(false);
        }
    }

    @FXML
    void clickSaveDel(MouseEvent event) {
        if(selectedSaveImage != null){
            if(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(selectedSaveImage)].getName() != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete?", new ButtonType[]{ButtonType.YES,ButtonType.NO});
                alert.showAndWait().ifPresent(response -> {
                    if( response == ButtonType.YES ){

                        int index = listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(selectedSaveImage);
                        System.out.println("index is: "+index);
                        textFieldSaveName.setText("save "+index);
                        textFieldSaveDate.setText("-- / -- / ----    -- : -- : --");
                        selectedSaveImage.setImage(new Image("file:src/gal/data/Image/System/NoData.png"));

                        File file = new File("src/gal/save/"+index+".savedata");
//                    System.gc();
                        if(file.delete()){
//                            System.out.println("Delete done.");
                        }else{
//                            System.out.println("delete fuck");
                        }

                        file = new File("src/gal/save/"+index+".png");
                        if(file.delete()){
//                            System.out.println("Delete done.");
                        }else{
//                            System.out.println("delete fuck");
                        }

                        listSave[index].delete();
                    }
                });
            }
        }
    }

    @FXML
    void clickSaveSave(MouseEvent event) throws IOException {
        if(selectedSaveImage != null){
            if(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(selectedSaveImage)].getName() != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to fix this Save?", new ButtonType[]{ButtonType.YES,ButtonType.NO});
                alert.showAndWait().ifPresent(response -> {
                    if( response == ButtonType.YES ){
                        Save save = new Save(listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(selectedSaveImage), textFieldSaveName.getText(), DATE_FORMAT.format(new Date()), navi);
                        try {
                            save.save();
                            prtScreen_Save(save.getIndex());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(selectedSaveImage)]= save;
                        textFieldSaveName.setText(save.getName());
                        textFieldSaveDate.setText(save.getDate());
                        selectedSaveImage.setImage(new Image("file:src/gal/save/"+save.getIndex()+".png"));
                    }
                });
            }else{
                Save save = new Save(listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(selectedSaveImage), textFieldSaveName.getText(), DATE_FORMAT.format(new Date()), navi);
                save.save();
                prtScreen_Save(save.getIndex());
                listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(selectedSaveImage)]= save;
                textFieldSaveName.setText(save.getName());
                textFieldSaveDate.setText(save.getDate());
                selectedSaveImage.setImage(new Image("file:src/gal/save/"+save.getIndex()+".png"));
            }
        }
    }
    @FXML
    void clickSaveLoad(MouseEvent event) throws IOException, InterruptedException {
        if(selectedSaveImage != null){
            selectedSaveImage.setOpacity(0.7);
            if(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(selectedSaveImage)].getName() != null){
                int index = listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(selectedSaveImage);
                navi = new Navi();
                navi.set(listSave[index].getCurrent_Navi());
                textFieldSaveName.setText("save "+index);
                textFieldSaveDate.setText("-- / -- / ----    -- : -- : --");
                if(buttons != null){
                    paneGame.getChildren().remove(buttons);
                    for(int i=0; i<buttons.size(); i++){
                        buttons.get(i).getButton().setVisible(false);
                        buttons.get(i).getButton().disarm();
                    }
                    buttons = null;
//                    if(gameClicker == null){
//                        gameClicker = new EventHandler<MouseEvent>() {
//                            @Override
//                            public void handle(MouseEvent event) {
//                                if((event.getButton() == MouseButton.PRIMARY) && game_TextAndMenu.get(0).isVisible()  ){
//                                    if( !isNowBranch ){
//                                        System.out.println("go on");
//                                        try {
//                                            goon();
//                                        } catch (IOException | InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }else if(event.getButton() == MouseButton.SECONDARY ){
//                                    bubble_Switch();
//                                    System.out.println("Hide UI");
//                                }
//                            }
//                        };
//                    }
//                    paneGameTouch.addEventHandler(MouseEvent.MOUSE_CLICKED, gameClicker);
                    isNowBranch = false;
                }
                player_BGM.stop_Playing();


                paneGame.setVisible(true);
                paneSave.setVisible(false);
                imageBG.setImage(new Image("file:src/gal/data/Image/BG/"+navi.getCurrent_Scene().getBG()));

                if( !navi.getCurrent_Scene().getBGM().isEmpty() ){
                    player_BGM.play_BGM(navi.getCurrent_Scene().getBGM());
                }
                text_Speaker.setText(navi.getCurrent_Log().getSpeaker());
                // char image
                textAreaBuilder = new StringBuilder(navi.getCurrent_Sentence());

                image_Char.setImage(new Image("file:src/gal/data/Image/Character/"+navi.getCurrent_Log().getCharacterImage()));

                if( !navi.getCurrent_Log().getSE().isEmpty()){
                    player_SE.play_Once(navi.getCurrent_Log().getSE());
                }
                textPlay();
                if( navi.isTimeToBranch() ){
                    System.out.println("isTimeToBranch():  Yes.");
//                    if(gameClicker!=null){
////                        paneGameTouch.removeEventHandler(MouseEvent.MOUSE_CLICKED, gameClicker);
//                        isNowBranch = true;
//                    }
                    isNowBranch = true;

                    buttons = new ArrayList<>();
                    System.out.println("buttons = new");
                    for(int i=0; i<navi.getCurrent_Scene().getBranchList().size(); i++){
                        Button button = new Button(navi.getCurrent_Scene().getBranchList().get(i).getContent());
                        button.setPrefWidth(paneGame.getWidth()/3);
                        button.setPrefHeight((paneGame.getHeight()-image_Bubble.getY()-20)/navi.getCurrent_Scene().getBranchList().size()/2-15);
                        button.setLayoutX(paneGame.getWidth()/2-button.getPrefWidth()/2);
                        button.setLayoutY(20+button.getPrefHeight()*i*2);
                        button.setPrefHeight(button.getPrefHeight()-10);
                        if( button.getPrefHeight() >70){
                            button.setPrefHeight(70);
                        }
                        paneGame.getChildren().add(button);
                        BB bb = new BB();
                        bb.setButton(button);
                        bb.setContent(navi.getCurrent_Scene().getBranchList().get(i).getContent());
                        buttons.add(bb);
                        button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                            @Override
                            public void handle(MouseEvent event) {
                                int index=-1;
                                System.out.println((event.getTarget()));

                                for(int i=0; i<navi.getCurrent_Scene().getBranchList().size(); i++){
                                System.out.println("233 i:"+i+"  button i:"+buttons.get(i));

                                    if(button.getText().equals(buttons.get(i).content)){
                                        index = i;
                                        break;
                                    }
                                }
                                for(int i=0; i<buttons.size(); i++){
                                    paneGame.getChildren().remove(buttons.get(i).button);
                                }
                                
                                buttons = null;
                               
                            System.out.println("233 index: "+index);
                                String nextSceneName = navi.getCurrent_Scene().getBranchList().get(index).getNextSceneName();
                                try {
                                    navi.next(nextSceneName);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                imageBG.setImage(new Image("file:src/gal/data/Image/BG/"+navi.getCurrent_Scene().getBG()));
                                if(!player_BGM.getCurrent_BGM().equals(navi.getCurrent_Scene().getBGM())){
                                    player_BGM.stop_Playing();
                                    player_BGM.play_BGM(navi.getCurrent_Scene().getBGM());
                                }
                                text_Speaker.setText(navi.getCurrent_Log().getSpeaker());
                                // char image
                                textAreaBuilder = new StringBuilder();
                                textAreaBuilder.append(navi.getCurrent_Sentence());

                                image_Char.setImage(new Image("file:src/gal/data/Image/Character/"+navi.getCurrent_Log().getCharacterImage()));

                                if( navi.getCurrent_Log().getSE() != null){
                                    player_SE.play_Once(navi.getCurrent_Log().getSE());
                                }
                                try {
                                    textPlay();
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
//                                if(gameClicker == null){
//                                    gameClicker = new EventHandler<MouseEvent>() {
//                                        @Override
//                                        public void handle(MouseEvent event) {
//                                            if((event.getButton() == MouseButton.PRIMARY) && game_TextAndMenu.get(0).isVisible()  ){
//                                                if(!isNowBranch){
//                                                    System.out.println("go on");
//                                                    try {
//                                                        goon();
//                                                    } catch (IOException | InterruptedException e) {
//                                                        e.printStackTrace();
//                                                    }
//                                                }
//                                            }else if(event.getButton() == MouseButton.SECONDARY ){
//                                                bubble_Switch();
//                                                System.out.println("Hide UI");
//                                            }
//                                            System.out.println("gameClicker finish");
//                                        }
//                                    };
//                                }
//                                paneGameTouch.addEventHandler(MouseEvent.MOUSE_CLICKED, gameClicker);
                                isNowBranch = false;
                            }
                        });
                    }
                }else{
                    System.out.println("isTimeToBranch():  No.");
                }
            }
            selectedSaveImage = null;

        }
    }

    private final static char PANE_GAME = 'G';
    private final static char PANE_SAVE = 'S';
    private final static char PANE_LOAD = 'L';
    private final static char PANE_MAIN = 'M';

    private Music player_BGM;
    private Music player_SE;
    private TextDisplay textDisplayer;

    private Animation textPlay;

    private List<BB> buttons;

    private boolean isNowBranch=false;

    private Stage stage;

    private Navi navi;
    private StringBuilder textAreaBuilder;

    private Group groupImageSave = new Group();
    private ToggleGroup groupPageSave = new ToggleGroup();

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM / dd / yyyy    hh : mm : ss");

    private EventHandler<MouseEvent> gameClicker = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            if((event.getButton() == MouseButton.PRIMARY) && game_TextAndMenu.get(0).isVisible()  ){
                if(!isNowBranch){
                    System.out.println("go on");
                    try {
                        goon();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }else if(event.getButton() == MouseButton.SECONDARY ){
                bubble_Switch();
                System.out.println("Hide UI");
            }

            System.out.println("gameClicker finish");
        }
    };

    private List<Node> game_TextAndMenu;/* = FXCollections.observableArrayList(paneGame.getChildren());*/

    private List<ImageView> listImageSave = new ArrayList<>();
    private List<RadioButton> listPageSave = new ArrayList<>();
    private Save[] listSave = new Save[120];
    private ImageView selectedSaveImage;
    private Save selectedSave;


    private void loadSave() throws IOException {
        for(int i=0; i<120; i++){
            listSave[i] = new Save(i);
        }
    }

//    private void textSaveLoadBrowse(MouseEvent event){
//        if(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getName() == null){
//            textFieldSaveName.setText("save "+(listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())+1));
//            textFieldSaveName.setText("No Data");
//        }else{
//            textFieldSaveName.setText(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getName());
//            textFieldSaveDate.setText(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getDate());
//        }
//    }
//    private void textSaveLoadSelect(){
//        if(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getName() == null){
//            textFieldSaveName.setText("save "+(listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())+1));
//            textFieldSaveName.setText("No Data");
//        }else{
//            textFieldSaveName.setText(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getName());
//            textFieldSaveDate.setText(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getDate());
//        }
//    }
//    private void textSaveLoadNull(){
//        textFieldSaveName.setText("save "+(listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())+1));
//        textFieldSaveName.setText("No Data");
//    }

    private BufferedImage bufferedImage;

    private void prtScreen() throws AWTException, IOException {
        Robot robot = new Robot();
        Bounds bounds = paneGame.getBoundsInLocal();
        Bounds screenBounds = paneGame.localToScreen(bounds);
        int x = (int) screenBounds.getMinX();
        int y = (int) screenBounds.getMinY();
        int width = (int) screenBounds.getWidth();
        int height = (int) screenBounds.getHeight();
        java.awt.Rectangle rectangle = new java.awt.Rectangle(x, y, width, height);
        bufferedImage = robot.createScreenCapture(rectangle);
        WritableImage wi = SwingFXUtils.toFXImage(bufferedImage, null);
//        imageView2.setImage(wi);

    }
    private void prtScreen_Save(int index) throws IOException {
        if (bufferedImage != null) {
//            if (isReview) {
//                path = new StringBuilder(Game.PATH_RECORD + "\\"
//                        + user.getName() + "\\" + "printScreen\\"
//                        + Game.DATE_FORMAT.format(new Date()) + ".png").toString();
//
//                Clipboard cb = Clipboard.getSystemClipboard();
//                /* 将图片放在剪切板上 */
//                ClipboardContent content = new ClipboardContent();
//                content.putImage(wi);
//                cb.setContent(content);
//            } else {
//                path = new StringBuilder(Game.PATH_RECORD
//                        + "\\" + user.getName() + "\\" + game.getDate() + ".png").toString();
//            }

            File success = new File("src/gal/save/"+index+".png");
            if (!success.exists()) {
                // 文件夹不存在
                success.mkdirs();
            }
            // 储存图片
            ImageIO.write(bufferedImage, "png",
                    success);
        }
    }

    public void initGame() throws IOException {
        image_BG_Main.setImage(new Image("file:src/gal/data/Image/BG/main.png"));
        image_Bubble.setImage(new Image("file:src/gal/data/Image/System/bubbleText.png"));
        loadSave();
        textFieldSaveDate.setEditable(false);
        listImageSave.add(imageSave1);
        listImageSave.add(imageSave2);
        listImageSave.add(imageSave3);
        listImageSave.add(imageSave4);
        listImageSave.add(imageSave5);
        listImageSave.add(imageSave6);
        listImageSave.add(imageSave7);
        listImageSave.add(imageSave8);
        listImageSave.add(imageSave9);
        listImageSave.add(imageSave10);
        listImageSave.add(imageSave11);
        listImageSave.add(imageSave12);

        groupImageSave.getChildren().addAll(listImageSave);
        paneGameTouch.addEventHandler(MouseEvent.MOUSE_CLICKED, gameClicker);
//        groupImageSave.setOnMouseEntered(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                ((ImageView)event.getTarget()).setOpacity(1);
//                System.out.println(event.getTarget()+"   MouseEntered");
//
//            }
//        });
        for(int i=0 ;i<listImageSave.size(); i++){
            listImageSave.get(i).setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

//                    selectedSave = listSave[listImageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())];
//                    if(selectedSave.getName()!=null){
//                        textFieldSaveDate.setText(selectedSave.getDate());
//                        textFieldSaveName.setText(selectedSave.getName());
//                    }else{
//
//                    }
                    if(selectedSaveImage == null){
                        ((ImageView)event.getTarget()).setOpacity(1);
                        if(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getName() == null){
                            textFieldSaveName.setText("save "+(listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())+1));
                            textFieldSaveDate.setText("-- / -- / ----    -- : -- : --");
                        }else{
                            textFieldSaveDate.setText(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getDate());
                            textFieldSaveName.setText(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getName());
                        }
                    }
                }
            });
            listImageSave.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {

                    if( selectedSaveImage == event.getTarget()){
                        selectedSaveImage.setOpacity(0.7);
                        selectedSaveImage = null;

                        textFieldSaveName.setText("save "+(listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())+1));
                        textFieldSaveDate.setText("-- / -- / ----    -- : -- : --");

                    }else{
                        if( selectedSaveImage != null){
                            selectedSaveImage.setOpacity(0.7);
                        }
                        selectedSaveImage = (ImageView)event.getTarget();
                        selectedSaveImage.setOpacity(1);

                        if(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getName()!=null){
                            textFieldSaveName.setText(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getName());
                            textFieldSaveDate.setText(listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())].getDate());
                        }else{
                            textFieldSaveName.setText("save "+(listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+listImageSave.indexOf(event.getTarget())+1));
                            textFieldSaveDate.setText("-- / -- / ----    -- : -- : --");
                        }
                    }

                }
            });
            listImageSave.get(i).setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(selectedSaveImage != null){
                        if(selectedSaveImage != (ImageView)event.getTarget()){
                            ((ImageView)event.getTarget()).setOpacity(0.7);
//                        System.out.println(event.getTarget()+"   MouseExited");

                        }
                    }else{
                        ((ImageView)event.getTarget()).setOpacity(0.7);
                        textFieldSaveName.setText("");
                        textFieldSaveDate.setText("-- / -- / ----    -- : -- : --");
                    }
                }
            });
        }

//        groupImageSave.setOnMouseExited(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                ((ImageView)event.getTarget()).setOpacity(0.7);
//                System.out.println(event.getTarget()+"   MouseExited");
//
//            }
//        });

        paneSavePic.getChildren().addAll(listImageSave);

        listPageSave.add(choiceSavePage1);
        listPageSave.add(choiceSavePage2);
        listPageSave.add(choiceSavePage3);
        listPageSave.add(choiceSavePage4);
        listPageSave.add(choiceSavePage5);
        listPageSave.add(choiceSavePage6);
        listPageSave.add(choiceSavePage7);
        listPageSave.add(choiceSavePage8);
        listPageSave.add(choiceSavePage9);
        listPageSave.add(choiceSavePage10);
//        groupPageSave.getChildren().addAll(listPageSave);
        for(int i=0; i<10; i++){
            listPageSave.get(i).setToggleGroup(groupPageSave);
            listPageSave.get(i).setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    Save save;
                    for(int i=0; i<listImageSave.size(); i++){
                        save = listSave[listPageSave.indexOf(groupPageSave.getSelectedToggle())*12+i];
                        if(save.getDate()!=null){
                            listImageSave.get(i).setImage(new Image("file:src/gal/save/"+save.getIndex()+".png"));
                        }else{
                            listImageSave.get(i).setImage(new Image("file:src/gal/data/Image/System/NoData.png"));
//                            System.out.println("shit...2");
                        }
//                        System.out.println((RadioButton)groupPageSave.getSelectedToggle()+" 12333");
                    }
                }
            });
        }




        game_TextAndMenu = new ArrayList<>();
        game_TextAndMenu.add(text_Speaker);
        game_TextAndMenu.add(textArea);
        game_TextAndMenu.add(button_Hide_Game);
        game_TextAndMenu.add(button_Load_Game);
        game_TextAndMenu.add(button_Menu_Game);
        game_TextAndMenu.add(button_Save_Game);
        game_TextAndMenu.add(button_Log_Game);
        game_TextAndMenu.add(image_Bubble);
        game_TextAndMenu.add(image_Bubble_Speaker);
        textAreaBuilder = new StringBuilder();
//        paneGameTouch.addEventHandler(MouseEvent.MOUSE_CLICKED, gameClicker);
    }

    private void bubble_Switch(){
        if(game_TextAndMenu.get(0).isVisible()){
            for(int i=0; i<game_TextAndMenu.size();i++){
                game_TextAndMenu.get(i).setVisible(false);
            }
        }else{
            for(int i=0; i<game_TextAndMenu.size();i++){
                game_TextAndMenu.get(i).setVisible(true);
            }
        }

    }

    private void textPlay() throws InterruptedException {
//        for(int i=0; i<textAreaBuilder.length(); i++){
//            textArea.setText(textAreaBuilder.substring(0,i));
//            Thread.sleep(50);
//        }
        textPlay = new Transition() {
            {
                setCycleDuration(Duration.millis(35*textAreaBuilder.length()));
            }
            @Override
            protected void interpolate(double frac) {
                int n = Math.round(textAreaBuilder.length() * (float)frac);
                textArea.setText(textAreaBuilder.toString().substring(0,n));
            }
        };
        textPlay.play();
    }

    class BB{
        private Button button;
        private String content;

        public Button getButton() {
            return button;
        }

        public void setButton(Button button) {
            this.button = button;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    private void goon() throws IOException, InterruptedException {
        switch( navi.next() ){
            case Navi.CHANGE_SENTENCE:
                System.out.println("sentence");
                textAreaBuilder = new StringBuilder();
                textAreaBuilder.append(navi.getCurrent_Sentence());
//                textArea.setText(textAreaBuilder.toString());
//                textDisplayer.play(textArea, textAreaBuilder.toString());
                textPlay();
//                timeline = new Timeline();

//                for(int i=0; i<textAreaBuilder.length(); i++){
//                    timeline.getKeyFrames().add(new KeyFrame(Duration.millis(40*i), new KeyValue()))
//                }

                break;
            case Navi.CHANGE_LOG:
                System.out.println("log");
                text_Speaker.setText(navi.getCurrent_Log().getSpeaker());
                textAreaBuilder = new StringBuilder();
                textAreaBuilder.append(navi.getCurrent_Sentence());
                image_Char.setImage(new Image("file:src/gal/data/Image/Character/"+navi.getCurrent_Log().getCharacterImage()));

                if( navi.getCurrent_Log().getSE() != null){
                    player_SE.play_Once(navi.getCurrent_Log().getSE());
                }
//                textArea.setText(textAreaBuilder.toString());
//                textDisplayer.play(textArea, textAreaBuilder.toString());
                textPlay();

                break;
            case Navi.CHANGE_SCENE:
                System.out.println("scene");
                imageBG.setImage(new Image("file:src/gal/data/Image/BG/"+navi.getCurrent_Scene().getBG()));
                if(navi.getCurrent_Scene().getBGM() != null ){
                    if(!navi.getCurrent_Scene().getBGM().equals(player_BGM.getCurrent_BGM())){
                        player_BGM.stop_Playing();
                        player_BGM.play_BGM(navi.getCurrent_Scene().getBGM());
                    }
                }else{
                    player_BGM.stop_Playing();
                }
                text_Speaker.setText(navi.getCurrent_Log().getSpeaker());
                // char image
                textAreaBuilder = new StringBuilder();
                textAreaBuilder.append(navi.getCurrent_Sentence());

                image_Char.setImage(new Image("file:src/gal/data/Image/Character/"+navi.getCurrent_Log().getCharacterImage()));

                if( navi.getCurrent_Log().getSE() != null){
                    player_SE.play_Once(navi.getCurrent_Log().getSE());
                }
                textPlay();
                break;
            case Navi.CHANGE_BRANCH:
                System.out.println("branch");
                // wait player choose the branch
//                paneGameTouch.removeEventHandler(MouseEvent.MOUSE_CLICKED, gameClicker);
                isNowBranch = true;

                buttons = new ArrayList<>();
                System.out.println("buttons = new");
                for(int i=0; i<navi.getCurrent_Scene().getBranchList().size(); i++){
                    Button button = new Button(navi.getCurrent_Scene().getBranchList().get(i).getContent());
                    button.setPrefWidth(paneGame.getWidth()/3);
                    button.setPrefHeight((paneGame.getHeight()-image_Bubble.getY()-20)/navi.getCurrent_Scene().getBranchList().size()/2-15);
                    button.setLayoutX(paneGame.getWidth()/2-button.getPrefWidth()/2);
                    button.setLayoutY(20+button.getPrefHeight()*i*2);
                    button.setPrefHeight(button.getPrefHeight()-10);
                    if( button.getPrefHeight() >70){
                        button.setPrefHeight(70);
                    }
                    paneGame.getChildren().add(button);
                    BB bb = new BB();
                    bb.setButton(button);

                    bb.setContent(navi.getCurrent_Scene().getBranchList().get(i).getContent());
                    buttons.add(bb);
                    button.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            int index=-1;
                            for(int i=0; i<navi.getCurrent_Scene().getBranchList().size(); i++){
//                                System.out.println("i:"+i+"  target:"+event.getTarget());
                                if(button.getText().equals(buttons.get(i).content)){
                                    index = i;
                                    break;
                                }
                            }
                            for(int i=0; i<buttons.size(); i++){
                                paneGame.getChildren().remove(buttons.get(i).button);
                            }
                            
                            buttons = null;
                           
//                            System.out.println("index: "+index);
                            String nextSceneName = navi.getCurrent_Scene().getBranchList().get(index).getNextSceneName();
                            try {
                                navi.next(nextSceneName);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            imageBG.setImage(new Image("file:src/gal/data/Image/BG/"+navi.getCurrent_Scene().getBG()));
                            if(!player_BGM.getCurrent_BGM().equals(navi.getCurrent_Scene().getBGM())){
                                player_BGM.stop_Playing();
                                player_BGM.play_BGM(navi.getCurrent_Scene().getBGM());
                            }
                            text_Speaker.setText(navi.getCurrent_Log().getSpeaker());
                            // char image
                            textAreaBuilder = new StringBuilder();
                            textAreaBuilder.append(navi.getCurrent_Sentence());

                            image_Char.setImage(new Image("file:src/gal/data/Image/Character/"+navi.getCurrent_Log().getCharacterImage()));

                            if( navi.getCurrent_Log().getSE() != null){
                                player_SE.play_Once(navi.getCurrent_Log().getSE());
                            }
                            try {
                                textPlay();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
//                            paneGameTouch.addEventHandler(MouseEvent.MOUSE_CLICKED, gameClicker);
                            isNowBranch = false;
                        }
                    });
                }


                break;
            case Navi.CHANGE_END:
                System.out.println("end!!!!!!!!!!!!!!!");
                player_BGM.stop_Playing();
                navi = null;
                try {
                    switchPane(PANE_MAIN);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                player_BGM.play_BGM("main.wav");
                break;
            default:
                System.out.println("Game.goon(): navi.next() returns except case.");
        }
    }





    private void switchPane(char paneName) throws IOException, InterruptedException {
        ObservableList<Node> nodePane = FXCollections.observableArrayList(anchorPane.getChildren());
        nodePane.removeAll();
        paneGame.setVisible(false);
        paneLoad.setVisible(false);
        paneSave.setVisible(false);
        paneMain.setVisible(false);
        switch(paneName){
            case PANE_GAME:
                nodePane.add(paneGame);
                paneGame.setVisible(true);
                player_BGM.stop_Playing();
                navi = new Navi();
                imageBG.setImage(new Image("file:src/gal/data/Image/BG/"+navi.getCurrent_Scene().getBG()));
                image_Char.setPreserveRatio(true);
                image_Char.imageProperty().addListener(new ChangeListener<Image>() {
                    @Override
                    public void changed(ObservableValue<? extends Image> observable, Image oldValue, Image newValue) {
                        double screenHeight =paneGame.getHeight();
                        double screenWidth = paneGame.getWidth();
                        double ratio = newValue.getWidth()/ newValue.getHeight();
                        image_Char.setX(screenWidth/2-image_Char.getFitWidth()/2);
                        image_Char.setY(screenHeight-image_Char.getFitWidth()/ratio);
                    }
                });

                image_Char.setImage(new Image("file:src/gal/data/Image/Character/"+navi.getCurrent_Log().getCharacterImage()));

                // image Character;

                player_BGM.play_BGM(navi.getCurrent_Scene().getBGM());
                if(navi.getCurrent_Scene().getSE() != null){
                    player_SE.play_Once(navi.getCurrent_Scene().getSE());
                }
                text_Speaker.setText(navi.getCurrent_Log().getSpeaker());
                textAreaBuilder.append(navi.getCurrent_Sentence());
//                textArea.setText(textAreaBuilder.toString());
//                textDisplayer.play(textArea, textAreaBuilder.toString());
                textPlay();
//                gameClicker = new EventHandler<MouseEvent>() {
//                    @Override
//                    public void handle(MouseEvent event) {
//                        if((event.getButton() == MouseButton.PRIMARY) && game_TextAndMenu.get(0).isVisible()  ){
//                            if( !isNowBranch ){
//                                System.out.println("go on");
//                                try {
//                                    goon();
//                                } catch (IOException | InterruptedException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//                        }else if(event.getButton() == MouseButton.SECONDARY ){
//                            System.out.println("Hide UI");
//                            bubble_Switch();
//                        }
//                        System.out.println("gameClicker finish work\n");
//                    }
//                };
//                paneGameTouch.addEventHandler(MouseEvent.MOUSE_CLICKED, gameClicker);
                isNowBranch = false;
                break;
            case PANE_LOAD:
                nodePane.add(paneLoad);
                paneLoad.setVisible(true);
                break;
            case PANE_SAVE:
                nodePane.add(paneSave);
                paneSave.setVisible(true);
                break;
            case PANE_MAIN:
                nodePane.add(paneMain);
                paneMain.setVisible(true);
                break;
            default:
                System.out.println("switchPane(): No such Pane\n");
        }

    }

    public void init_Main(Stage stage) throws IOException, InterruptedException {
        this.stage = stage;
        switchPane(PANE_MAIN);
        player_BGM = new Music();
        player_SE = new Music();
        textDisplayer = new TextDisplay();
        player_BGM.play_BGM("main.wav");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                player_BGM.stop_Playing();
                if(player_SE.had_Played()){
                    player_SE.stop_Playing();
                }
                System.exit(0);
            }
        });
    }

    private void start(){
//        line.getCurrent_Scene();
    }


}
