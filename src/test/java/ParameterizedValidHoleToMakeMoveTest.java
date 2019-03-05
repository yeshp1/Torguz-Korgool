import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertArrayEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import logic.*;

@RunWith(Parameterized.class)
public class ParameterizedValidHoleToMakeMoveTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new Player(), 0, true},
                {new Player(), 1, true},
                {new Player(), 7, true},
                {new Player(), 8, true},
                {new Player(), 9, false},
                {new Player(), 13, false},
                {new Player(), 15, false},
                {new Player(), 17, false},
        });
    }

    private Player player;
    private int indexSelected;
    private boolean output;

    public ParameterizedValidHoleToMakeMoveTest(Player player, int indexSelected, boolean output)
    {
        this.player = player;
        this.indexSelected = indexSelected;
        this.output = output;
    }

    @Test
    public void testValidHole(){
        Player player2 = new Player();
        Board board = new Board(player, player2);
        assertEquals(output,board.validHoleSelected(player,indexSelected));
    }

}