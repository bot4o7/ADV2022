package gal.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import gal.system.Branch;
import gal.system.Log;
import gal.system.ProjectInfo;
import gal.system.Scene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ScriptWriter {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane paneMain;

    @FXML
    private Button buttonOpenProject;

    @FXML
    private Button buttonQuit;

    @FXML
    private Hyperlink linkHint_ProjectPath;

    @FXML
    private Button buttonNewProject;

    @FXML
    private ScrollPane scrollPaneProjectList;

    @FXML
    private TableView<ProjectInfo> tableProjectList;

    @FXML
    private TableColumn<?, ?> col_ID;

    @FXML
    private TableColumn<ProjectInfo, String> col_ProjectName;

    @FXML
    private TableColumn<ProjectInfo, String> col_CreateDate;

    @FXML
    private TableColumn<ProjectInfo, String> col_EditDate;

    @FXML
    private TableColumn<ProjectInfo, String> col_Author;

    @FXML
    private TextArea textAreaProjectIntro;

    @FXML
    private Pane paneWork;

    @FXML
    private Button buttonBackMain;

    @FXML
    private Label labelSceneName;

    @FXML
    private TextField textSceneName;

    @FXML
    private Label labelBG;

    @FXML
    private TextField textBG;

    @FXML
    private Label labelBGM;

    @FXML
    private TextField textBGM;

    @FXML
    private Label labelSE;

    @FXML
    private TextField textSE;

    @FXML
    private Label labelNextSceneName;

    @FXML
    private TextField textNextSceneName;

    @FXML
    private Hyperlink linkEditBranch;

    @FXML
    private Label labelPostSceneName;

    @FXML
    private Label labelPostNextSceneName;

    @FXML
    private Label labelPostBG;

    @FXML
    private Label lablePostBGM;

    @FXML
    private Label labelPostSE;

    @FXML
    private Hyperlink linkDeleteScene;

    @FXML
    private Separator separatorLeft;

    @FXML
    private Pane paneLog;

    @FXML
    private ScrollBar scorllBarPaneLog;

    @FXML
    private Button buttonLastLog;

    @FXML
    private Label labelLog;

    @FXML
    private Button buttonNextLog;

    @FXML
    private Hyperlink linkDeleteLog;

    @FXML
    private Label labelSpeaker;

    @FXML
    private TextField textSpeaker;

    @FXML
    private Label labelCharacterImage;

    @FXML
    private TextField textCharacterImage;

    @FXML
    private Label labelPostCharImg;

    @FXML
    private Label labelSE_Log;

    @FXML
    private TextField textSE_Log;

    @FXML
    private TextField textProjectName;

    @FXML
    private Label labelPostSE_Log;

    @FXML
    private Label labelSentenceList;

    @FXML
    private Label labelSentence0;

    @FXML
    private TextArea textAreaSentence0;

    @FXML
    private Hyperlink linkDeleteSentence0;

    @FXML
    private Button buttonNewSentence;

    @FXML
    private Button buttonNewLogBefore;

    @FXML
    private Button buttonNewLogAfter;

    @FXML
    private ScrollPane scrollPaneSceneList;

    @FXML
    private TableView<SceneName> tableSceneList;

    @FXML
    private TableColumn<SceneName, String> col_SceneName;

    @FXML
    private Button buttonSaveScene;

    @FXML
    private Button buttonNewScene;

    @FXML
    void clickBackMain(MouseEvent event) throws IOException {
        selectedProjectInfo = null;
        //Save Edit
        saveDontName();
        if((selectedScene!=null) && (selectedProjectInfo!=null)){
            saveToFile();
        }
        switchPane(PANE_MAIN);
    }

    @FXML
    void clickDeleteLog(MouseEvent event) {
        selectedScene.getLogList().remove(selectedLog);
        removeAreaSentences();
        if (currentLog_Index >= selectedScene.getLogList().size()) {
            currentLog_Index--;
        }
        openLog(currentLog_Index);
    }

    private void sceneClear(){
        textSE_Log.setText("");
        textSE.setText("");
        textBG.setText("");
        textNextSceneName.setText("");
        textBGM.setText("");
        textAreaSentence0.setText("");
        textCharacterImage.setText("");
        textSpeaker.setText("");
        removeAreaSentences();
    }

    @FXML
    void clickDeleteScene(MouseEvent event) throws IOException {
        for(int i=0; i<listScene.size(); i++){
            if(listScene.get(i).getName().equals(selectedScene.getName())){
                listScene.remove(i);
                tableSceneList.setItems(listScene);
                break;
            }
        }
        File file = new File("src/gal/workplace/"+selectedProjectInfo.getName()+"/Scene/"+selectedScene.getName());
        selectedScene=null;
        selectedLog=null;
        sceneClear();
        File temp=null;
        if(file.exists()){
            System.gc();
            if(file.delete()){
//                System.out.println("Selected Scene had been deleted");
            }else{
//                System.out.println("Warning: Selected Scene had not been deleted");
                temp = file;
            }
            if(listScene.size()==0){
                for (int i=0; ;i++){
                    file = new File("src/gal/workplace/"+selectedProjectInfo.getName()+"/Scene/NewScene"+i+".scene");
                    if(!file.exists()){
                        selectedScene = new Scene(i);
                        break;
                    }
                }
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(new GsonBuilder().create().toJson(selectedScene));
                bw.flush();
                listScene.add(new SceneName(selectedScene.getName()));
            }else{
                file = new File("src/gal/workplace/"+selectedProjectInfo.getName()+"/Scene/"+listScene.get(0).getName());
                if( file.exists() ){
                    java.lang.System.out.println("yes");
                }else{
                    java.lang.System.out.println("no");
                }
                java.lang.System.out.println(file.getAbsolutePath());
                String content = new BufferedReader(new FileReader(file)).readLine();
                java.lang.System.out.println("\n\nget Scene:\n"+content);

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                selectedScene = gson.fromJson(content, Scene.class);
            }
            tableSceneList.setItems(listScene);
//            tableSceneList.getItems().add(listScene.get(listScene.size()-1));
            openScene();
            System.gc();
            if(temp!=null){
                if(temp.delete()){
                    System.out.println("Selected Scene had been deleted");
                }else{
                    System.out.println("Warning: Selected Scene had not been deleted");
                }
            }
        }

    }

    @FXML
    void clickDeleteSentence(MouseEvent event) {
        // Abundant
    }

    private boolean isBranchEditing=false;

    private List<Branch> listBranch;

    @FXML
    void clickEditBranch(MouseEvent event) throws IOException {
        if(!isBranchEditing){
            Stage branchStage = new Stage();
            System.out.println("loader path: \n"+this.getClass());
//            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/BranchEditor.fxml"));
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("BranchEditor.fxml"));

            Parent root = (Parent)loader.load();

            BranchEditor target = loader.getController();
            target.init(selectedScene);

            listBranch = new ArrayList<>();


            javafx.scene.Scene scene = new javafx.scene.Scene(root);

            branchStage.setScene(scene);

            branchStage.setTitle("BranchEditor");
            branchStage.show();
//            branchStage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, new EventHandler<WindowEvent>() {
//                @Override
//                public void handle(WindowEvent event) {
//                    save
//
//                    listBranch = null;
//                }
//            });

        }
    }

    @FXML
    void clickLastLog(MouseEvent event) {
        saveDontName();
        if( currentLog_Index>0){
            currentLog_Index--;
            removeAreaSentences();
            openLog(currentLog_Index);
        }
    }

    @FXML
    void clickNewLogBefore(MouseEvent event) {
        removeAreaSentences();
        selectedScene.getLogList().add(currentLog_Index, new Log());
        openLog(currentLog_Index);
    }

    @FXML
    void clickNewLogAfter(MouseEvent event) {
        currentLog_Index++;
        removeAreaSentences();
        selectedScene.getLogList().add(currentLog_Index, new Log());
        openLog(currentLog_Index);
    }

    @FXML
    void clickNewProject(MouseEvent event) throws IOException {
//
        File file;
        for (int i=0; ;i++){
            file = new File("src/gal/workplace/NewProject"+i);
            if(!file.exists()){
                file.mkdirs();
                File f;
                f = new File(file.getPath()+"/Audio/BGM");
                f.mkdirs();
                f = new File(file.getPath()+"/Audio/BGS");
                f.mkdirs();
                f = new File(file.getPath()+"/Audio/SE");
                f.mkdirs();
                f = new File(file.getPath()+"/Audio/ME");
                f.mkdirs();
                f = new File(file.getPath()+"/Audio/Voice");
                f.mkdirs();
                f = new File(file.getPath()+"/Image/Animation");
                f.mkdirs();
                f = new File(file.getPath()+"/Image/BG");
                f.mkdirs();
                f = new File(file.getPath()+"/Image/Character");
                f.mkdirs();
                f = new File(file.getPath()+"/Image/System");
                f.mkdirs();
                f = new File(file.getPath()+"/Scene");
                f.mkdirs();
                selectedProjectInfo = new ProjectInfo(i);
                selectedProjectInfo.setAuthor("ScriptWriter");
                selectedProjectInfo.setIntro("NULL");
                selectedProjectInfo.setDateCreate(DATE_FORMAT.format(new Date()));
                selectedProjectInfo.setDateEdit(selectedProjectInfo.getDateCreate());

                for (int ii=0; ;ii++){
                    file = new File("src/gal/workplace/"+selectedProjectInfo.getName()+"/Scene/NewScene"+ii+".scene");
                    if(!file.exists()){
                        selectedScene = new Scene(ii);
                        break;
                    }
                }
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(new GsonBuilder().create().toJson(selectedScene));
                bw.flush();
                file = new File("src/gal/workplace/"+selectedProjectInfo.getName()+"/"+selectedProjectInfo.getName()+".project_info");
                if( file.exists() ){
                    file.delete();
                }
                file.getParentFile().mkdirs();
                file.createNewFile();
                fw = new FileWriter(file);
                bw = new BufferedWriter(fw);
                Gson gson = new GsonBuilder().create();

                bw.write(gson.toJson(selectedProjectInfo));
                bw.flush();
fw.close();
bw.close();
                break;
            }
        }
        listProjectInfo.add(selectedProjectInfo);
        tableProjectList.setItems(listProjectInfo);
        tableProjectList.getSelectionModel().select(selectedProjectInfo);
        openProject();
        switchPane(PANE_WORK);
    }

    @FXML
    void clickNewSentence(MouseEvent event) throws IOException {
        selectedLog.getSentences().add("");
        listAreaSentence.add(new AreaSentence(selectedLog.getSentences().size()-1, selectedLog.getSentences().get(selectedLog.getSentences().size()-1)));

    }

    @FXML
    void clickNextLog(MouseEvent event) {
        saveDontName();
        if( currentLog_Index<selectedScene.getLogList().size()-1){
            currentLog_Index++;
            removeAreaSentences();

            openLog(currentLog_Index);
        }
    }

    @FXML
    void clickNewScene(MouseEvent event) throws IOException {
        File file;
        for (int i=0; ;i++){
            file = new File("src/gal/workplace/"+selectedProjectInfo.getName()+"/Scene/NewScene"+i+".scene");
            if(!file.exists()){
                selectedScene = new Scene(i);
                break;
            }
        }
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(new GsonBuilder().create().toJson(selectedScene));
        bw.flush();
        listScene.add(new SceneName(selectedScene.getName()));
//        tableSceneList.getItems().add()
        openScene();
    }

    @FXML
    void clickOpenProject(MouseEvent event) throws IOException {
        if( tableProjectList.getSelectionModel().getSelectedItems().size() == 1){
//            selectedProjectInfo = tableProjectList.getSelectionModel().getSelectedItem();

            openProject();

            switchPane(PANE_WORK);


        }
    }

    @FXML
    void clickQuit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void clickSave(MouseEvent event) throws IOException {
        saveDontName();
        saveToFile();
    }

    public void init() throws IOException {
//        paneMain.setVisible(true);
//        paneWork.setVisible(false);

        paneWork.getScene().getWindow().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if(paneWork.isVisible()){
                if(event.isControlDown()){
                    System.out.println("yes, control.");
                    if(event.getCode().equals(KeyCode.S)){
                        System.out.println("yes, control + S.");
                        saveDontName();
                        try {
                            saveToFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }else if(event.getCode().isArrowKey()){
                        if(event.isAltDown()){
                            if(event.getCode().equals(KeyCode.LEFT)){
                                System.out.println("Last Log: control + Left");
                                saveDontName();
                                if( currentLog_Index>0){
                                    currentLog_Index--;
                                    removeAreaSentences();
                                    openLog(currentLog_Index);
                                }
                            }else if(event.getCode().equals(KeyCode.RIGHT)){
                                System.out.println("Next Log: control + Right");
                                saveDontName();
                                if( currentLog_Index<selectedScene.getLogList().size()-1){
                                    currentLog_Index++;
                                    removeAreaSentences();

                                    openLog(currentLog_Index);
                                }
                            }else if(event.getCode().equals(KeyCode.UP)){
                                System.out.println("Last Sentence: control + Up");

                            }else if(event.getCode().equals(KeyCode.DOWN)){
                                System.out.println("Next Log: control + Down");

                            }
                        }

                    }else if(event.getCode().equals(KeyCode.BACK_SPACE)){
                        selectedProjectInfo = null;
                        //Save Edit
                        saveDontName();
                        if((selectedScene!=null) && (selectedProjectInfo!=null)){
                            try {
                                saveToFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        try {
                            switchPane(PANE_MAIN);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });



        switchPane(PANE_MAIN);
    }


    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy  hh:mm:ss");


    private ProjectInfo selectedProjectInfo;
    private Scene selectedScene;
    private Log selectedLog;
//    private String selectedSentence;

    private class AreaSentence{
        int index;
        TextArea textArea;
        Label label;
        Hyperlink linkDelete;
        AreaSentence thisAreaSentence = this;

        public AreaSentence(int index, String content){
            this.index = index;
            label = new Label("Sentence "+index);
            label.setFont(labelSentence0.getFont());
            label.setAlignment(labelSentence0.getAlignment());
            label.setPrefWidth(labelSentence0.getPrefWidth());
            paneLog.getChildren().add(label);
            label.setLayoutX(labelSentence0.getLayoutX());

            linkDelete = new Hyperlink("DeleteSentence");
            linkDelete.setFont(linkDeleteSentence0.getFont());
            linkDelete.setAlignment(linkDeleteSentence0.getAlignment());
            linkDelete.setPrefWidth(linkDeleteSentence0.getPrefWidth());
            paneLog.getChildren().add(linkDelete);
            linkDelete.setLayoutX(linkDeleteSentence0.getLayoutX());

            textArea = new TextArea(content);
            textArea.setFont(textAreaSentence0.getFont());
            textArea.setPrefWidth(textAreaSentence0.getPrefWidth());
            textArea.setPrefHeight(textAreaSentence0.getPrefHeight());
            paneLog.getChildren().add(textArea);
            textArea.setLayoutX(textAreaSentence0.getLayoutX());

            label.setLayoutY(textAreaSentence0.getLayoutY()+125*index);
            linkDelete.setLayoutY(label.getLayoutY());
            textArea.setLayoutY(label.getLayoutY()+22);

            paneLog.setPrefHeight(500+140*listAreaSentence.size());
            linkDelete.setOnMouseClicked(event -> {

                selectedLog.getSentences().remove(listAreaSentence.indexOf(thisAreaSentence));
//                    selectedScene.getLogList().get(currentLog_Index).getSentences().remove(index);
                //   此处， 似乎 selectedLog 的remove操作对 selectedScene也起到了作用
                label.setVisible(false);
                linkDelete.setVisible(false);
                textArea.setVisible(false);
                paneLog.getChildren().removeAll(label, linkDelete, textArea);
                for(int i=listAreaSentence.indexOf(thisAreaSentence); i<listAreaSentence.size(); i++){
                    System.out.println(listAreaSentence.get(i).textArea.getText());
                    listAreaSentence.get(i).index--;
                    listAreaSentence.get(i).label.setText("Sentence "+listAreaSentence.get(i).index);


                    listAreaSentence.get(i).label.setLayoutY(textAreaSentence0.getLayoutY()+125*listAreaSentence.get(i).index);
                    listAreaSentence.get(i).linkDelete.setLayoutY(listAreaSentence.get(i).label.getLayoutY());
                    listAreaSentence.get(i).textArea.setLayoutY(listAreaSentence.get(i).label.getLayoutY()+22);
                }
                listAreaSentence.remove(thisAreaSentence);
                paneLog.setPrefHeight(500+140*listAreaSentence.size());
            });

            label.setVisible(true);
            linkDelete.setVisible(true);
            textArea.setVisible(true);
            System.out.println("sentence 0: "+labelSentence0.getLayoutX() +" "+labelSentence0.getLayoutY());
            System.out.println("new sentence "+index+": "+label.getLayoutX()+" "+label.getLayoutY());
        }

        public AreaSentence(){

        }

        public void removeFromPaneLog(){
            label.setVisible(false);
            linkDelete.setVisible(false);
            textArea.setVisible(false);
            textArea.setText("");
            paneLog.getChildren().removeAll(label, linkDelete, textArea);
        }

    }

    private void removeAreaSentences(){
        if(listAreaSentence!=null){
            for(int i=1; i<listAreaSentence.size(); i++){
                listAreaSentence.get(i).removeFromPaneLog();
            }
        }
    }

    private List<AreaSentence> listAreaSentence;

    private static final char PANE_MAIN = 'M';
    private static final char PANE_WORK = 'W';

//    private List<ProjectInfo> listProjectInfo;
    private ObservableList<ProjectInfo> listProjectInfo;
    private ObservableList<SceneName> listScene;



    private void openProject(){
        listScene = FXCollections.observableArrayList();

        File file = new File("src/gal/workplace/"+selectedProjectInfo.getName()+"/Scene");
        System.out.println("ScriptWriter: openProject(): "+file.getAbsolutePath());
        if(file.isDirectory()){
            for(int i = 0; i< Objects.requireNonNull(file.list()).length; i++){
                listScene.add(new SceneName(Objects.requireNonNull(file.list())[i]));
                System.out.println("SceneName: "+ Objects.requireNonNull(file.list())[i]);
            }
            System.out.println("fuck: ");

//            col_SceneName.setCellValueFactory(new PropertyValueFactory<SceneName, String>("name"));

//            col_SceneName = new TableColumn<SceneName, String>("name");
            col_SceneName.setCellValueFactory(new PropertyValueFactory<>("name"));
            tableSceneList.getColumns().setAll(col_SceneName);
            tableSceneList.setItems(listScene);
            tableSceneList.getSelectionModel().selectFirst();
            System.out.println("fuck done.");
            textProjectName.setText(selectedProjectInfo.getName());
            tableSceneList.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
//                    System.out.println(tableSceneList.getSelectionModel().getSelectedItem().name);
                System.out.println(tableSceneList.getSelectionModel().getSelectedCells().get(0).toString());
                if( (tableSceneList.getSelectionModel().getSelectedItems().size() == 1)
                && (tableSceneList.getSelectionModel().getSelectedItem() != null)){
                    // Save Edit

                    System.out.println("enter handler");
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    Gson gson = gsonBuilder.create();

                    File file1 = new File(
                            "src/gal/workplace/"+selectedProjectInfo.getName()
                                    +"/Scene/"+tableSceneList.getSelectionModel().getSelectedItem().getName());

                    // Save scene

                    if(file1.exists()){
                        try {
                            selectedScene = gson.fromJson(new BufferedReader(new FileReader(file1)).readLine(), Scene.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        System.out.println(selectedScene);
                        openScene();
                    }
                }
            });
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            file = new File(
                    "src/gal/workplace/"+selectedProjectInfo.getName()
                            +"/Scene/"+tableSceneList.getSelectionModel().getSelectedItem().getName());

            // Save scene

            if(file.exists()){
                try {
                    selectedScene = gson.fromJson(new BufferedReader(new FileReader(file)).readLine(), Scene.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(selectedScene);
                openScene();
            }
        }else{
            System.out.println("shit.    Directory \""+file.getAbsolutePath()+"\"  doesn't exists.");
        }
    }

    private void openScene(){
        removeAreaSentences();
        System.out.println("Enter openScene()");
        textSceneName.setText(selectedScene.getName().substring(0, selectedScene.getName().length()-6));
        textBG.setText(selectedScene.getBG());

        if(selectedScene.getBGM().length()>4){
            textBGM.setText(selectedScene.getBGM().substring(0, selectedScene.getBGM().length()-4));
        }else{
            textBGM.setText("");
        }
        if(selectedScene.getSE().length()>4){
            textSE.setText(selectedScene.getSE().substring(0,selectedScene.getSE().length()-4));
        }else{
            textSE.setText("");
        }
        if(selectedScene.getNextSceneName().length()>6){
            textNextSceneName.setText(selectedScene.getNextSceneName().substring(0,selectedScene.getNextSceneName().length()-6));
        }else{
            textNextSceneName.setText("");
        }

        openLog(0);

        System.out.println("Out openScene()");

    }

    private int currentLog_Index;

    private void saveTemp(){
    // Scene info save
        saveOnlyName();
        saveDontName();
    }

    private void saveOnlyName(){
        if( !textSceneName.getText().isEmpty() ){
            selectedScene.setName(textSceneName.getText()+".scene");
        }
    }

    private void saveDontName(){
        // Scene info save
//        if( !textSceneName.getText().isEmpty() ){
//            selectedScene.setName(textSceneName.getText()+".scene");
//        }
        if( !textNextSceneName.getText().isEmpty()){
            selectedScene.setNextSceneName(textNextSceneName.getText()+".scene");
        }
        selectedScene.setBG(textBG.getText());
        if( !textBGM.getText().isEmpty()){
            selectedScene.setBGM(textBGM.getText()+".wav");
        }else{
            selectedScene.setBGM("");
        }
        if( !textSE.getText().isEmpty()){
            selectedScene.setBGM(textSE.getText()+".wav");
        }else{
            selectedScene.setSE("");
        }

        // Sentence info save
        selectedLog.getSentences().clear();
        selectedLog.getSentences().add(textAreaSentence0.getText());
        for(int i=1; i<listAreaSentence.size(); i++){
            selectedLog.getSentences().add(listAreaSentence.get(i).textArea.getText());
        }


        // Log info save
        selectedLog.setSpeaker(textSpeaker.getText());
        selectedLog.setCharacterImage(textCharacterImage.getText());
        if( !textSE_Log.getText().isEmpty()){
            selectedLog.setSE(textSE_Log.getText()+".wav");
        }else{
            selectedLog.setSE("");
        }
    }

    private void saveToFile() throws IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();

        Gson gson = gsonBuilder.create();
        File file = new File("src/gal/workplace/"+selectedProjectInfo.getName()+"/Scene/"+selectedScene.getName());

        if( file.exists() ){
            file.delete();
            System.out.println("Original scene:"+selectedScene.getName()+" had benn deleted.");
        }
        for(int i=0; i<listScene.size(); i++){
            if(listScene.get(i).getName().equals(selectedScene.getName())){
                listScene.remove(listScene.get(i));
                tableSceneList.setItems(listScene);
            }
        }
        saveOnlyName();
        file = new File("src/gal/workplace/"+selectedProjectInfo.getName()+"/Scene/"+selectedScene.getName());


        file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        String out = gson.toJson(selectedScene);
        bw.write(out);
        bw.flush();
        System.out.println(file.getAbsolutePath());

//        info.setName("Project1");
        selectedProjectInfo.setDateEdit(ScriptWriter.DATE_FORMAT.format(new Date()));
//        info.setAuthor("ScriptWriter");
//        info.setIntro("This is a test intro.\nfuck you, my dear.\nhehe. \n  :)\n");
        String originalProjectName = selectedProjectInfo.getName();
        if(!textProjectName.getText().isEmpty()){
            selectedProjectInfo.setName(textProjectName.getText());
        }
        out = gson.toJson(selectedProjectInfo);
        file = new File("src/gal/workplace/"+originalProjectName+"/"+originalProjectName+".project_info");

//        if( file.exists() ){
//            file.delete();
//        }
        file.renameTo(new File("src/gal/workplace/"+originalProjectName+"/"+selectedProjectInfo.getName()+".project_info"));

        file.getParentFile().mkdirs();
        file.createNewFile();
        fw = new FileWriter(file);
        bw = new BufferedWriter(fw);
        bw.write(out);
        bw.flush();

        file.getParentFile().renameTo(new File("src/gal/workplace/"+selectedProjectInfo.getName()));

        listScene.add(new SceneName(selectedScene.getName()));
        tableSceneList.setItems(listScene);
        tableSceneList.getSelectionModel().selectLast();
    }

    private void openLog(int index){
        currentLog_Index = index;
        selectedLog = selectedScene.getLogList().get(index);
//        selectedSentence = selectedLog.getSentences().get(0);
        labelLog.setText("Log "+(index+1)+"/"+selectedScene.getLogList().size());

        textSpeaker.setText(selectedLog.getSpeaker());
        if(selectedLog.getSE().length()>4){
            textSE_Log.setText(selectedLog.getSE().substring(0,selectedLog.getSE().length()-4));
        }else{
            textSE_Log.setText("");
        }
        textCharacterImage.setText(selectedLog.getCharacterImage());

        if(selectedLog.getSentences().size()>0){
            textAreaSentence0.setText(selectedLog.getSentences().get(0));
        }else{
            textAreaSentence0.setText("");
        }
        listAreaSentence = new ArrayList<>();
        listAreaSentence.add(new AreaSentence());
        for(int i=1; i<selectedLog.getSentences().size(); i++){
            listAreaSentence.add(new AreaSentence(i, selectedLog.getSentences().get(i)));
            System.out.println("AreaSentence: add new AreaSentence: "+i);
        }
    }

    private void switchPane(char paneName) throws IOException {
        ObservableList<Node> nodePane = FXCollections.observableArrayList(anchorPane.getChildren());
        nodePane.removeAll();
        paneMain.setVisible(false);
        paneWork.setVisible(false);
        switch(paneName){
            case PANE_MAIN:
                paneMain.setVisible(true);

//                listProjectInfo = new ArrayList<>();
                listProjectInfo = FXCollections.observableArrayList();

                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                File file = new File("src/gal/workplace");
                System.out.println(file.getAbsolutePath());
                if(file.isDirectory()){
                    String[] name = file.list();
                    String content;

                    for(int i=0; i<name.length; i++){
                        content = new BufferedReader(new FileReader("src/gal/workplace/"+name[i]+"/"+name[i]+".project_info")).readLine();
                        System.out.println(content+"\n\n");
                        listProjectInfo.add(gson.fromJson(content, ProjectInfo.class));
                    }
                    col_ProjectName.setCellValueFactory(new PropertyValueFactory<ProjectInfo, String>("name"));
                    col_Author.setCellValueFactory(new PropertyValueFactory<ProjectInfo, String>("author"));
                    col_CreateDate.setCellValueFactory(new PropertyValueFactory<ProjectInfo, String>("dateCreate"));
                    col_EditDate.setCellValueFactory(new PropertyValueFactory<ProjectInfo, String>("dateEdit"));
                    tableProjectList.setItems(listProjectInfo);
                    tableProjectList.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            if((tableProjectList.getSelectionModel().getSelectedItems().size() == 1)
                                    &&(tableProjectList.getSelectionModel().getSelectedItem() != null)){
                                selectedProjectInfo = tableProjectList.getSelectionModel().getSelectedItem();
                                textAreaProjectIntro.setText(tableProjectList.getSelectionModel().getSelectedItem().getIntro());
                            }else{
                                textAreaProjectIntro.setText("");
                            }
                        }
                    });
                    tableProjectList.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent event) {
                            if(event.getCode().equals(KeyCode.ENTER)){
                                if((tableProjectList.getSelectionModel().getSelectedItems().size() == 1)
                                        &&(tableProjectList.getSelectionModel().getSelectedItem() != null)){
                                    if(selectedProjectInfo==null){
                                        selectedProjectInfo = tableProjectList.getSelectionModel().getSelectedItem();
                                        textAreaProjectIntro.setText(tableProjectList.getSelectionModel().getSelectedItem().getIntro());
                                    }else{
                                        openProject();
                                        try {
                                            switchPane(PANE_WORK);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }else{
                                    textAreaProjectIntro.setText("");
                                }
                            }

                        }
                    });
                }else{
                    System.out.println("shit.    Directory \""+file.getAbsolutePath()+"\"  doesn't exists.");
                }
                selectedProjectInfo=null;
                tableProjectList.requestFocus();
                break;
            case PANE_WORK:
                paneWork.setVisible(true);

                break;
        }

    }

}
