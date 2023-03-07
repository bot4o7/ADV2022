package gal.system;

public class Branch {
    private String content;
    private String nextSceneName;

    public Branch(){

    }

    public Branch(String content, String nextSceneName){
        this.content = content;
        this.nextSceneName = nextSceneName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNextSceneName() {
        return nextSceneName;
    }

    public void setNextSceneName(String nextSceneName) {
        this.nextSceneName = nextSceneName;
    }
}
