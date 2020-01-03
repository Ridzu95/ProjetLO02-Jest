package fr.utt.lo02.xfmv.jest.vue;

public class Message {
    private String key;
    private Object value;

    public Message(String key, Object value){
        this.key = key;
        this.value = value;
    }



    public Object getValue() {
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
