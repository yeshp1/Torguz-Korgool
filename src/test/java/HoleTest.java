import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import logic.*;

/**
 * The test class HoleTest.
 *
 * @version 2018.11.15
 * @authors Yeshvanth Prabakar, Jay Machpherson, Mikes, Tom,
 */

public class HoleTest
{
    Hole testHole;
    /**
     * Default constructor for test class HoleTest
     */
    public HoleTest()
    {}

    @Before
    public final void setUp()
    {
        testHole = new Hole(new Player());
    }

    @After
    public final void tearDown()
    {
        testHole = null;
    }

    @Test
    public void defaultHoleSizeShouldReturnNine() {
        assertEquals (9, testHole.size());
    }

    //default case even
    @Test
    public void isEvenShouldReturnFalse() {
        assertEquals (false, testHole.isEven());
    }

    //odd case
    @Test
    public void isEvenShouldReturnTrue() {
        testHole.addKorgool(new Korgool());
        assertEquals (true, testHole.isEven());
    }

    //default case no tutz
    @Test
    public void isTutzShouldReturnFalse() {
        assertEquals (false, testHole.isTutz());
    }

    //hole is a tutz
    @Test
    public void isTutzShouldReturnTrue() {
        testHole.setTutz(true);
        assertEquals (true, testHole.isTutz());
    }
/*
    TODO - NOT NEEDED ANYMORE
    @Test
    public void wasLastShouldReturnFalse() {
        assertEquals (false, testHole.wasLast());
    }
*/

    //adding Korgools to Hole
    @Test
    public void addingKorgools() {
        //add 1
        testHole.addKorgool(new Korgool());
        assertEquals (10, testHole.size());

        //add 4
        testHole.addKorgool(new Korgool());
        testHole.addKorgool(new Korgool());
        testHole.addKorgool(new Korgool());
        assertEquals (13, testHole.size());

        //add 1013
        for(int i=0; i<1000; i++) {
            testHole.addKorgool(new Korgool());
        }
        assertEquals (1013, testHole.size());
    }


    //removing Korgools from empty Hole of size 0
    @Test
    public void removingfromEmptyHole() {
        testHole.clearHole();
        assertEquals (0, testHole.size());
    }

    //removing Korgools from default Hole of size 9
    @Test
    public void removingKorgoolsDefault() {
        testHole.clearHole();
        assertEquals (0, testHole.size());
    }

    //removing Korgools from Hole of size 11
    @Test
    public void removingKorgoolsAdditional() {
        testHole.addKorgool(new Korgool());
        testHole.addKorgool(new Korgool());
        testHole.clearHole();
        assertEquals (0, testHole.size());
    }

    //removing Korgools from Hole of size 1000
    @Test
    public void removingKorgoolsLarge() {
        for(int i = 0; i<1000;i++) {
            testHole.addKorgool(new Korgool());
        }
        testHole.clearHole();
        assertEquals (0, testHole.size());
    }

}
