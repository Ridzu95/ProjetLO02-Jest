package fr.utt.lo02.xfmv.jest.model.cartes;

public enum Couleurs {
    Coeur("Coeur", 1),
    Carreau("Carreau", 2),
    Trefle("Trefle", 3),
    Pique("Pique", 4);

    private String couleur;
    private int ordre;

    Couleurs(String couleur, int ordre) {
        this.couleur = couleur;
        this.ordre = ordre;
    }

    public String getCouleur() { return couleur; }
    public int getOrdre() { return ordre; }
}
