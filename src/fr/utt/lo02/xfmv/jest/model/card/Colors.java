package fr.utt.lo02.xfmv.jest.model.card;

public enum Colors {
    Heart("♥", 1),
    Diamond("♦", 2),
    Club("♣", 3),
    Spade("♠", 4),
    Joker("★", 5);

    private String color;
    private int ordre;

    Colors(String color, int order) {
        this.color = color;
        this.ordre = order;
    }

    public String getColor() { return color; }
    public int getOrder() { return ordre; }
}
