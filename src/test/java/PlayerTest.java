import static org.junit.Assert.assertEquals;
import org.junit.Test;
import logic.*;


/**
 * Unit testing class for the Player class.
 */
/**
 * The test class PlayerTest.
 *
 * @version 2018.11.15
 * @authors Yeshvanth Prabakar, Jay Machpherson, Mikes, Tom,
 */

public class PlayerTest {

    @Test
    public void nameIsSetShouldReturnTrue(){
        Player player = new Player();
        assertEquals("Default Name", player.getName());
    }

    @Test
    public void nameIsChangedShouldReturnDifferentValue(){
        Player player = new Player();
        player.setName("This is a different name");
        assertEquals("This is a different name", player.getName());
    }

   // @Test
    public void kazanIsSetShgouldReturnKazanOfSizeZero(){
        Player player = new Player();
        assertEquals(null, player.getKazanCount());
    }
    
    @Test
    public void tutzIsInitialisedShouldReturnFalse(){

        Player player = new Player();
        assertEquals(false, player.hasTutz());
    }

    @Test
    public void tutzIsSetShouldReturnTrue(){
        Player player = new Player();
        player.setTutz(14);
        assertEquals(true, player.hasTutz());
    }

}