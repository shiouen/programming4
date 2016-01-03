package be.kdg.prog4.spel;

public class Dice {
    private int value;

    public Dice() {
        this.value = -1;
    }

    public int getValue() { return this.value; }

    // bug fix: (int)(Math.random() * 6 + 2); returns int between 2 and 7
    // generally, randomness like this is very hard to test, it's best to
    // assume Random() does it job well
    public void roll() { this.value = (int) (Math.random() * 6 + 1); }
}
