package fr.utt.lo02.xfmv.jest.model.cartes;

public enum Valeurs {
    Joker(0),
    As(1),
    Deux(2),
    Trois(3),
    Quatre(4);

    private int valeur;

    Valeurs(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() { return valeur;}
}