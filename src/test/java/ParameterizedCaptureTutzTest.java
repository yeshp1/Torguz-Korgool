import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import logic.*;

@RunWith(Parameterized.class)
public class ParameterizedCaptureTutzTest {
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            // Hole size must be even and greater than 3, and not belong to player
            // { holeSize, belongsToPlayer, hasTutz, notMatching, holeNumber, yield}

            // cant capture - player owner
            // {currentTutz, holeSize, belongToPlayer, notMatching, holeNumber, expectedTutz}
            { -1, 3,false, true, 10, 10},
            { -1, 3,false, true, 13, 13},
            { -1, 3,true, true, 13, -1},
            { -1, 3,false, true, 8, -1},
            { -1, 3,false, true, 17, -1},
            { -1, 3,false, false, 17, -1},
            { 15, 3,false, true, 13, 15},
            { -1, 3,true, true, 13, -1}

        });
    }

    private int currentTutz;
    private int holeSize;
    private boolean belongToPlayer;
    private boolean notMatching;
    private int holeNumber;
    private int expectedTutz;


    public ParameterizedCaptureTutzTest(int currentTutz, int holeSize, boolean belongToPlayer, boolean notMatching, int holeNumber, int expectedTutz) {

        this.currentTutz = currentTutz;
        this.holeSize = holeSize;
        this.belongToPlayer = belongToPlayer;
        this.notMatching = notMatching;
        this.holeNumber = holeNumber;
        this.expectedTutz = expectedTutz;

    }

    @Test
    public void testCaptureKorgools() {
        Player player = new Player();
        Player notPlayer = new Player();
        Hole h;
        h = belongToPlayer ? new Hole(player) : new Hole(notPlayer);
        h.clearHole();
        for (int i=0; i<holeSize;i++){
            h.addKorgool(new Korgool());
        }
        player.setTutz(currentTutz);

        logic.Capture.captureKorgools(h, player, notMatching, holeNumber);

        assertEquals(player.getTutz(), expectedTutz);
    }

}
