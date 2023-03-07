package gal.system;

import java.util.ArrayList;
import java.util.List;

public class Log {
    private String speaker;
    private String characterImage;
    private String SE;
    private List<String> sentences;

    public String getSpeaker() { return speaker; }

    public String getCharacterImage() { return characterImage; }

    public String getSE() { return SE; }

    public List<String> getSentences() { return sentences; }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public void setCharacterImage(String characterImage) {
        this.characterImage = characterImage;
    }

    public void setSE(String SE) {
        this.SE = SE;
    }

    public void setSentences(List<String> sentences) {
        this.sentences = sentences;
    }

    public Log(){
        this.speaker = "";
        this.characterImage = "";
        this.SE = "";
        this.sentences = new ArrayList<>();
    }

    public Log(String speaker, String characterImage, String SE, List<String> sentences){
        this.speaker = speaker;
        this.characterImage = characterImage;
        this.SE = SE;
        this.sentences = sentences;
    }

}
