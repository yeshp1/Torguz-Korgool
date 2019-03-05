import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.*;
import logic.*;

import java.util.Arrays;


/**
 * Unit testing class for the Board class.
 */
public class BoardTest {

    Player player1;
    Player player2;
    Board board;
    @Before
    public final void setUp()
    {
        player1 = new Player();
        player2 = new Player();
        board = new Board(player1, player2);
    }

    @After
    public final void tearDown()
    {

        player1 = null;
        player2 = null;
        board = null;
    }
   

    @Test   //hole size is correctly defined in the board
    public void DefaultHoleSizeIsDefined(){
        int expectedResult = new Hole(player1).size();
        int result = board.getHoleSizeByIndex(0);
        assertEquals(expectedResult,result);
    }
    @Test   //hole player is correctly defined in the board.
    public void DefaultHolePlayerIsDefined(){
        Player expectedResult = new Hole(player1).getOwner();
        Player result = board.getHolePlayerByIndex(0);
        assertEquals(expectedResult,result);
    }

    @Test //white side goes first
    public void whiteTurnShouldBeTrueInDefaultSettings(){
        boolean expectedResult = true;
        boolean result = board.getWhiteSideTurn();
        assertEquals(expectedResult,result);
    }

    @Test //non-white side goes second, update player turn
    public void whiteTurnShouldBeFalseAfterFirstMove(){
        board.move(1, player1);
        boolean expectedResult = false;
        boolean result = board.getWhiteSideTurn();
        assertEquals(expectedResult,result);
    }


    @Test //Assuming starting hole size is >1
    public void holeHasOneKargoolAfterBeingSelectedToDistribute(){
        int sizeOfHoleThree = board.getHoleSizeByIndex(3);
        int expectedResult = 1;
        board.redistribute(3, sizeOfHoleThree);
        int result = board.getHoleSizeByIndex(3);
        assertEquals(expectedResult,result);
    }

    @Test //Assuming starting hole size is 1
    public void holeIsEmptyAfterSelectedToDistributeOneKargool(){
        int sizeOfHoleThree = board.getHoleSizeByIndex(3);
        board.redistribute(3, sizeOfHoleThree);
        sizeOfHoleThree =  board.getHoleSizeByIndex(3);
        board.redistribute(3, sizeOfHoleThree);
        int result = board.getHoleSizeByIndex(3);
        int expectedResult = 0;
        assertEquals(expectedResult,result);
    }

    @Test //Assuming starting hole size is 1
    public void nextHoleIsSizeIsIncrementedAfterRedistribution(){
        int sizeOfHoleThree = board.getHoleSizeByIndex(3);
        int sizeOfHoleFour = board.getHoleSizeByIndex(4);
        board.redistribute(3, sizeOfHoleThree);
        int result = board.getHoleSizeByIndex(4);
        int expectedResult = sizeOfHoleFour +1; //incremented.
        assertEquals(expectedResult,result);
    }

}
