package be.kdg.chat.prog4.spel;

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
public class TestDice {
    @Autowired
    private Dice dice;

    @Before
    public void setUp() {
        // Dice Bean should be injected through wiring
    }

    @Test
    public void diceShouldHaveRolled() {
        this.dice.roll();

        String msg = "Dice should have rolled and its value should not equal 0 anymore";
        Assert.assertNotEquals(msg, this.dice.getValue(), 0);
    }

    @Test
    public void diceShouldRollWithinBoundaries() {
        // for this program it is enough to assume this
        // method will prove whether the roll boundaries
        // are set correctly in the dice's implementation

        int min = 1;
        int max = 6;

        int value;

        for (int i = 0; i < 1000; ++i) {
            this.dice.roll();

            value = this.dice.getValue();

            Assert.assertTrue("Dice should roll within boundaries", value >= min && value <= max);
        }
    }

    @After
    public void tearDown() {
        this.dice = null;
    }
}
