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
public class TestDiceConstruction {
    @Autowired
    private Dice dice;

    @Before
    public void setUp() {
        // Dice Bean should be injected through wiring
    }

    @Test
    public void diceShouldBeInitialized() {
        Assert.assertNotNull("Dice should be initialized", this.dice);
    }

    @Test
    public void diceAttributesShouldBeInitialized() {
        Assert.assertEquals("Dice value should be initialized as -1", -1, this.dice.getValue());
    }

    @After
    public void tearDown() {
        this.dice = null;
    }
}
