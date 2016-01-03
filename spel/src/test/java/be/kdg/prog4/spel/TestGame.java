package be.kdg.prog4.spel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "context.xml" })
public class TestGame {
    private Dice dice;
    @Autowired
    private Game game;

    @Before
    public void setUp() {
        this.dice = Mockito.mock(Dice.class);
        this.game.setDice(this.dice);
        this.game.start();

        Mockito.when(this.dice.getValue()).thenReturn(1, 2, 3, 4, 5, 6, 6, 6, 1, 6);
    }

    @Test
    public void gameShouldHaveStarted() {
        Assert.assertEquals("Game score should be equal to 0 when the game starts", 0, this.game.getScore());
    }

    @Test
    public void scoreWillBeCalculatedCorrectly() {
        int[] scores = { 0, 0, 0, 0, 0, 1, 2, 3, 3, 4 };
        String msg;

        for (int i = 0; i < 10; ++i) {
            this.game.rollDice();
            this.game.calculateScore();

            msg = String.format("Score will be calculated correctly after rolling the dice and calculating x%d", i + 1);
            Assert.assertEquals(msg, scores[i], this.game.getScore());
        }
    }

    @Test
    public void tearDown() {
        this.dice = null;
        this.game = null;
    }
}
