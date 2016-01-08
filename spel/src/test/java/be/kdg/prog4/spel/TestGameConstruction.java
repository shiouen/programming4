package be.kdg.prog4.spel;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"context.xml"})
public class TestGameConstruction {
    private Dice dice;
    @Autowired
    private Game game;

    @Before
    public void setUp() {
        // Game Bean should be injected through wiring

        this.dice = new Dice();
        this.game.setDice(this.dice);
    }

    @Test
    public void gameShouldBeInitialized() {
        Assert.assertNotNull("Game should be initialized", this.game);
    }

    @Test
    public void gameAttributesShouldBeInitialized() {
        Assert.assertEquals("Game score should be initialized as -1", -1, this.game.getScore());
    }

    @After
    public void tearDown() {
        this.dice = null;
        this.game = null;
    }
}
