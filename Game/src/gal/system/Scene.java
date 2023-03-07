package gal.system;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private String name;
    private String nextSceneName;
    private String BG;
    private String BGM;
    private String SE;
    private List<Log> logList;
    private List<Branch> branchList;

    public String getSE() { return SE; }

    public String getName() {
        return name;
    }

    public String getNextSceneName() {
        return nextSceneName;
    }

    public String getBG() {
        return BG;
    }

    public String getBGM() {
        return BGM;
    }

    public List<Log> getLogList() {
        return logList;
    }

    public List<Branch> getBranchList() {
        return branchList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNextSceneName(String nextSceneName) {
        this.nextSceneName = nextSceneName;
    }

    public void setBG(String BG) {
        this.BG = BG;
    }

    public void setBGM(String BGM) {
        this.BGM = BGM;
    }

    public void setSE(String SE) {
        this.SE = SE;
    }

    public void setLogList(List<Log> logList) {
        this.logList = logList;
    }

    public void setBranchList(List<Branch> branchList) {
        this.branchList = branchList;
    }

    public Scene(){}
    public Scene(int sameNameNum){
        nextSceneName = "";
        BG = "";
        BGM = "";
        SE = "";
        logList = new ArrayList<>();
        Log log = new Log();
        log.getSentences().add("");
        logList.add(log);
        branchList = new ArrayList<>();
        name = "NewScene"+sameNameNum+".scene";
    }

    public void testInit(){
        name = "Beginning.scene";
        nextSceneName = name;
        BG = "main.png";
        BGM = "main.wav";
        SE = "";
        logList = new ArrayList<>();
        Log log;
        List<String> sentences = new ArrayList<>();
        sentences.add("Hi.");
        sentences.add("Welcome, poor traveller.\n");
        log = new Log("Navi","USER2.gif","",sentences);
        logList.add(log);
        sentences = new ArrayList<>();
        sentences.add("errrr.");
        sentences.add("Excuse, where is this place?");
        log = new Log("I","","",sentences);
        logList.add(log);
        sentences = new ArrayList<>();
        sentences.add("You don't have to know.");
        sentences.add("Because 6 seconds later, you'll die.");
        log = new Log("Navi", "USER2.gif","",sentences);
        logList.add(log);
        branchList = new ArrayList<>();
        branchList.add(new Branch("test1", name));
        branchList.add(new Branch("test2", name));
//        branchList.add(new Branch("test3", name));
//        branchList.add(new Branch("test4", name));
//        branchList.add(new Branch("test4", name));


    }

}
