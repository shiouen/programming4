package be.kdg.chat.prog4.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Game {
    private int score;
    private Dice dice;

    public Game() {
       this.score = -1;
    }

    // bug fix: return this.dice.getValue();
    public int getScore() { return this.score; }

    @Autowired
    public void setDice(Dice dice) { this.dice = dice; }

    public void calculateScore() {
        if (this.dice.getValue() == 6) {
            this.score++;
        }
    }

    public void rollDice() { this.dice.roll(); }
    public void start() { this.score = 0; }
}
