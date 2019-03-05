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
public class ParameterizedCaptureKorgoolsTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // Hole size must be even and greater than 3, and not belong to player
            // { holeSize, belongsToPlayer, hasTutz, notMatching, holeNumber, yield}

            // cant capture - player owner
            {4, true, false, true, 6, 0},
            {10, true, false, true, 6, 0},
            {4, true, true, true, 6, 0},
            {10, true, true, false, 6, 0},

            // captures tutz
            {3, false, false, true, 6, 3},
            
            // doesnt capture tutz 
            {3, false, false, true, 6, 3},
            {3, false, true, true, 17, 0},
            {3, false, true, true, 8, 0},
            {3, false, true, false, 6, 0},

            // captures correctly
            {10, false, false, true, 6, 10},
            {4, false, false, true, 6, 4},
            {30, false, false, true, 6, 30},
            {30, false, false, true, 17, 30},
            {30, false, false, true, 8, 30}

        });
    }

    private int holeSize;
    private boolean belongsToPlayer;
    private boolean hasTutz;
    private boolean notMatching;
    private int holeNumber;
    private int yield;

    public ParameterizedCaptureKorgoolsTest(int holeSize, Boolean belongsToPlayer, Boolean hasTutz, Boolean notMatching, int holeNumber,int yield) {
        this.holeSize = holeSize;
        this.belongsToPlayer = belongsToPlayer;
        this.hasTutz = hasTutz;
        this.notMatching = notMatching;
        this.holeNumber = holeNumber;
        this.yield = yield;
    }

    @Test
    public void testCaptureKorgools() {
        Player player = new Player();
        Player notPlayer = new Player();
        Hole h;
        h = belongsToPlayer ? new Hole(player) : new Hole(notPlayer);
        h.clearHole();
        for (int i=0; i<holeSize;i++){
            h.addKorgool(new Korgool());
        }

        if (hasTutz){
            player.setTutz(20);
        }

        logic.Capture.captureKorgools(h, player, notMatching, holeNumber);

        int toCheck = player.getKazanCount();

        if (h.size() == 3 && !hasTutz){
            toCheck = player.getTutz() == holeNumber? toCheck : -1;
        }

        assertEquals(yield, toCheck);
    }

}
