package fr.utt.lo02.xfmv.jest.model.cartes;

public enum Couleurs {
    Coeur("♥", 1),
    Carreau("♦", 2),
    Trefle("♣", 3),
    Pique("♠", 4),
    Joker("★", 5);

    private String couleur;
    private int ordre;

    Couleurs(String couleur, int ordre) {
        this.couleur = couleur;
        this.ordre = ordre;
    }

    public String getCouleur() { return couleur; }
    public int getOrdre() { return ordre; }
}
