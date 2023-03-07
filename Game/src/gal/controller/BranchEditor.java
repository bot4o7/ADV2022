package gal.controller;

import gal.system.Branch;
import gal.system.Scene;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.WindowEvent;

public class BranchEditor {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Pane pane;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    @FXML
    private TextField textBranch1;

    @FXML
    private TextField textNextScene1;

    @FXML
    private TextField textBranch2;

    @FXML
    private TextField textNextScene2;

    @FXML
    private TextField textBranch3;

    @FXML
    private TextField textNextScene3;

    @FXML
    private TextField textBranch4;

    @FXML
    private TextField textNextScene4;

    @FXML
    private TextField textBranch5;

    @FXML
    private TextField textNextScene5;

    @FXML
    void clickCancel(MouseEvent event) {
        buttonCancel.getScene().getWindow().fireEvent(new WindowEvent(buttonCancel.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));
    }

    @FXML
    void clickSave(MouseEvent event) {
        scene.getBranchList().clear();
        String nextScene;
        if(!textBranch1.getText().isEmpty()){
            nextScene = textNextScene1.getText();
            if(!nextScene.isEmpty()){
                scene.getBranchList().add(new Branch(textBranch1.getText(),textNextScene1.getText()+".scene"));
            }else{
                scene.getBranchList().add(new Branch(textBranch1.getText(),""));
            }
        }
        if(!textBranch2.getText().isEmpty()){
            nextScene = textNextScene2.getText();
            if(!nextScene.isEmpty()){
                scene.getBranchList().add(new Branch(textBranch2.getText(),textNextScene2.getText()+".scene"));
            }else{
                scene.getBranchList().add(new Branch(textBranch2.getText(),""));
            }
        }
        if(!textBranch3.getText().isEmpty()){
            nextScene = textNextScene3.getText();
            if(!nextScene.isEmpty()){
                scene.getBranchList().add(new Branch(textBranch3.getText(),textNextScene3.getText()+".scene"));
            }else{
                scene.getBranchList().add(new Branch(textBranch3.getText(),""));
            }
        }
        if(!textBranch4.getText().isEmpty()){
            nextScene = textNextScene4.getText();
            if(!nextScene.isEmpty()){
                scene.getBranchList().add(new Branch(textBranch4.getText(),textNextScene4.getText()+".scene"));
            }else{
                scene.getBranchList().add(new Branch(textBranch4.getText(),""));
            }
        }
        if(!textBranch5.getText().isEmpty()){
            nextScene = textNextScene5.getText();
            if(!nextScene.isEmpty()){
                scene.getBranchList().add(new Branch(textBranch5.getText(),textNextScene5.getText()+".scene"));
            }else{
                scene.getBranchList().add(new Branch(textBranch5.getText(),""));
            }
        }

        buttonCancel.getScene().getWindow().fireEvent(new WindowEvent(buttonCancel.getScene().getWindow(), WindowEvent.WINDOW_CLOSE_REQUEST));

    }

    private Scene scene;
    public void init(Scene selectedScene){
        scene = selectedScene;
        for(int i=0; i<scene.getBranchList().size(); i++){
            switch(i){
                case 0:
                    textBranch1.setText(scene.getBranchList().get(0).getContent());
                    if(scene.getBranchList().get(0).getNextSceneName().isEmpty()){
                        textNextScene1.setText(scene.getBranchList().get(0).getNextSceneName());
                    }else{
                        textNextScene1.setText(scene.getBranchList().get(0).getNextSceneName()
                                .substring(0,scene.getBranchList().get(0).getNextSceneName().length()-6));
                    }
                    break;
                case 1:
                    textBranch2.setText(scene.getBranchList().get(1).getContent());
                    if(scene.getBranchList().get(1).getNextSceneName().isEmpty()){
                        textNextScene2.setText(scene.getBranchList().get(1).getNextSceneName());
                    }else{
                        textNextScene2.setText(scene.getBranchList().get(1).getNextSceneName()
                                .substring(0,scene.getBranchList().get(1).getNextSceneName().length()-6));
                    }
                    break;
                case 2:
                    textBranch3.setText(scene.getBranchList().get(2).getContent());
                    if(scene.getBranchList().get(2).getNextSceneName().isEmpty()){
                        textNextScene3.setText(scene.getBranchList().get(2).getNextSceneName());
                    }else{
                        textNextScene3.setText(scene.getBranchList().get(2).getNextSceneName()
                                .substring(0,scene.getBranchList().get(2).getNextSceneName().length()-6));
                    }
                    break;
                case 3:
                    textBranch4.setText(scene.getBranchList().get(3).getContent());
                    if(scene.getBranchList().get(3).getNextSceneName().isEmpty()){
                        textNextScene4.setText(scene.getBranchList().get(3).getNextSceneName());
                    }else{
                        textNextScene4.setText(scene.getBranchList().get(3).getNextSceneName()
                                .substring(0,scene.getBranchList().get(3).getNextSceneName().length()-6));
                    }
                    break;
                case 4:
                    textBranch5.setText(scene.getBranchList().get(4).getContent());
                    if(scene.getBranchList().get(4).getNextSceneName().isEmpty()){
                        textNextScene5.setText(scene.getBranchList().get(4).getNextSceneName());
                    }else{
                        textNextScene5.setText(scene.getBranchList().get(4).getNextSceneName()
                                .substring(0,scene.getBranchList().get(4).getNextSceneName().length()-6));
                    }
                    break;
                default:
                    System.out.println("BranchEditor(): the BranchEditor now can only show up to 5 branch");
            }
        }


    }
}
