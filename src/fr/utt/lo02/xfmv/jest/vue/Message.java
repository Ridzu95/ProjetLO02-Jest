package fr.utt.lo02.xfmv.jest.vue;

public class Message {
    private int value;
    private String key;

    public Message(String key, int value){
        this.key = key;
        this.value = value;
    }



    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String toString(){
        return ("message : " + this.key + " " + this.value);
    }
}
