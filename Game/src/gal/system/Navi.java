package gal.system;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class Navi {
    public static final String PATH_SCENE = "";
    public static final String PATH_AUDIO = "";
    public static final String PATH_IMAGE = "";
    public static final String CHANGE_SCENE = "SCENE";
    public static final String CHANGE_LOG = "LOG";
    public static final String CHANGE_SENTENCE = "SENTENCE";
    public static final String CHANGE_END = "END";
    public static final String CHANGE_BRANCH = "BRANCH";

    private Scene current_Scene;
    private Log current_Log;
    private String current_Sentence;
    private int index_Log;
    private int num_Log;
    private int index_Sentence;
    private int num_Sentence;


    public Scene getCurrent_Scene() {
        return current_Scene;
    }

    public Log getCurrent_Log() {
        return current_Log;
    }

    public String getCurrent_Sentence() {
        return current_Sentence;
    }

    public Navi() throws IOException {
        start();
    }

    public void start() throws IOException {
        File file = new File("src/gal/data/Scene/Beginning.scene");

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

        current_Scene = gson.fromJson(content, Scene.class);


        index_Log = 0;
        num_Log = current_Scene.getLogList().size();
        index_Sentence = 0;
        num_Sentence = current_Scene.getLogList().get(0).getSentences().size();
        current_Log = current_Scene.getLogList().get(0);
        current_Sentence = current_Log.getSentences().get(0);
    }

    public String next() throws IOException {
        String result;
        index_Sentence++;
        if( index_Sentence < num_Sentence ){
            current_Sentence = current_Log.getSentences().get(index_Sentence);
            result = CHANGE_SENTENCE;
        }else{
            index_Sentence=0;
            index_Log++;
            if( index_Log < num_Log ){
                current_Log = current_Scene.getLogList().get(index_Log);
                num_Sentence = current_Log.getSentences().size();
                current_Sentence = current_Log.getSentences().get(0);
                result = CHANGE_LOG;
            }else{
                index_Log=0;
                if( (current_Scene.getBranchList() != null)&&(current_Scene.getBranchList().size()>0) ) {
                    result = CHANGE_BRANCH;
                }else if( !current_Scene.getNextSceneName().isEmpty() ){
                    File file = new File("src/gal/data/Scene/"+current_Scene.getNextSceneName());

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

                    current_Scene = gson.fromJson(content, Scene.class);

                    num_Log = current_Scene.getLogList().size();
                    num_Sentence = current_Scene.getLogList().get(0).getSentences().size();
                    current_Log = current_Scene.getLogList().get(0);
                    current_Sentence = current_Log.getSentences().get(0);

                    result = CHANGE_SCENE;
                }else{
                    result = CHANGE_END;
                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n!!!!!!!!!!!!!!!!!!!Game End!!!\n!!!!!!!!!!!\n!!!!!!!!");
                }

            }
        }
        return result;
    }

    public void next(String nextSceneName) throws IOException {

        index_Sentence=0;
        index_Log=0;

        File file = new File("src/gal/data/Scene/"+nextSceneName);

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

        current_Scene = gson.fromJson(content, Scene.class);

        num_Log = current_Scene.getLogList().size();
        num_Sentence = current_Scene.getLogList().get(0).getSentences().size();
        current_Log = current_Scene.getLogList().get(0);
        current_Sentence = current_Log.getSentences().get(0);

    }

    public boolean isTimeToBranch(){
        boolean result = false;
        if( index_Log == 0){
            if( !current_Sentence.equals(current_Scene.getLogList().get(index_Log).getSentences().get(0))){
                return true;
            }
        }
        return false;
    }

    public void chooseBranch(int branch) throws IOException { // the index of the branch
        File file = new File("src/gal/data/Scene/"+current_Scene.getBranchList().get(branch).getNextSceneName());

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

        current_Scene = gson.fromJson(content, Scene.class);

        num_Log = current_Scene.getLogList().size();
        num_Sentence = current_Scene.getLogList().get(0).getSentences().size();
        current_Log = current_Scene.getLogList().get(0);
        current_Sentence = current_Log.getSentences().get(0);
    }

    public void set(Navi navi) {
        this.current_Scene = navi.current_Scene;
        this.current_Log = navi.current_Log;
        this.current_Sentence = navi.current_Sentence;
        this.index_Log = navi.index_Log;
        this.num_Log = navi.num_Log;
        this.index_Sentence = navi.index_Sentence;
        this.num_Sentence = navi.num_Sentence;
    }
}
