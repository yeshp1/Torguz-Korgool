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
public class ParameterizedRedistributeTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {0, new int[]{1,10,10,10,10,10,10,10,10,9,9,9,9,9,9,9,9,9}},
                {5, new int[]{9,9,9,9,9,1,10,10,10,10,10,10,10,10,9,9,9,9}},
        {8, new int[]{9,9,9,9,9,9,9,9,1,10,10,10,10,10,10,10,10,9}},
        {17, new int[]{10,10,10,10,10,10,10,10,9,9,9,9,9,9,9,9,9,1}},
        {15, new int[]{10,10,10,10,10,10,9,9,9,9,9,9,9,9,9,1,10,10}}
        });
    }

    private int indexSelected;
    private int[] holesSizes;

    public ParameterizedRedistributeTest(int indexSelected, int[] holesSizes)
    {
        this.indexSelected = indexSelected;
        this.holesSizes = holesSizes;
    }

    @Test
    public void testRedistribution(){
        Player player1 = new Player();
        Player player2 = new Player();
        Board board = new Board(player1, player2);
        int sizeOfHole = board.getHoleSizeByIndex(indexSelected);
        board.redistribute(indexSelected, sizeOfHole);
        assertArrayEquals(holesSizes,board.getHoleSizesArray());
    }

}