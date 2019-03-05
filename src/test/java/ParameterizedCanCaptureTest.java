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
public class ParameterizedCanCaptureTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // Hole size must be even and greater than 3, and not belong to player
            // { holeSize, belongsToPlayer, canCapture }
            {6, true, false}, 
            {3, true, false}, 
            {4, true, false}, 
            {1111, true, false}, 
            {2222, true, false}, 
            {8, false, true}, 
            {4, false, true}, 
            {22, false, true}, 
            {2222, false, true}, 
            {17, false, false}, 
            {3, false, true}, 
            {2, false, false}, 
            {1111, false, false},   
            {4, false, true} 
        });
    }

    private int holeSize;
    private boolean belongsToPlayer;
    private boolean canCapture;

    public ParameterizedCanCaptureTest(int holeSize, Boolean belongsToPlayer, Boolean canCapture) {
        this.holeSize = holeSize;
        this.belongsToPlayer = belongsToPlayer;
        this.canCapture = canCapture;
    }

    @Test
    public void testCanCapture() {
        Player player = new Player();
        Player notPlayer = new Player();
        Hole h;
        h = belongsToPlayer ? new Hole(player) : new Hole(notPlayer);
        h.clearHole();
        for (int i=0; i<holeSize;i++){
            h.addKorgool(new Korgool());
        }

        boolean toCheck = Capture.canCapture(h, player);
        assertEquals(canCapture, toCheck);
    }

}
