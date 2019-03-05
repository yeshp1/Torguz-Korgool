import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import logic.*;


/**
 * The test class GameTeset
 *
 * @version 2018.11.15
 * @authors Yeshvanth Prabakar, Jay Machpherson, Mikes, Tom,
 */

public class GameTest {
    public GameTest()
    {}

    private Player player = new Player();
    private Player computer = new Player();
    private Board board = new Board(computer, player);

    @Test
    public void defaultKazanShouldReturnZero() {
        assertEquals (0, player.getKazanCount());
        assertEquals (0, computer.getKazanCount());
    }
};