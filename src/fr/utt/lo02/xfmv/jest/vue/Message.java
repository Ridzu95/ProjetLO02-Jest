package fr.utt.lo02.xfmv.jest.vue;

public class Message {
    private String key;
    private int valeur;

    public Message(String key, int valeur){
        this.key = key;
        this.valeur = valeur;
    }



    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
