package logic;

import java.util.ArrayList;

/**
 * Capture covers the logic relating to capturing Korgool's as well as being responsible behind a
 * hole becoming a Tutz.
 */
public class Capture {

    public Capture() {

    }

    /**
     * Performs a capture of Korgools, also sets Tutz should the necesary conditions be met.
     *
     * @param target        hole being targeted for capture
     * @param currentPlayer current player
     */
    public static void captureKorgools(Hole target, Player currentPlayer, boolean notMatching, int holeNumber) {

        if (canCapture(target, currentPlayer)) {
            // can this be a tutz?
            if (target.size() == 3 &&  !target.isTutz() && !currentPlayer.hasTutz() && notMatching && holeNumber != 8 && holeNumber!=17) {
                ArrayList<Korgool> tempInventory = target.getInventory();
                currentPlayer.addKazan(tempInventory);
                target.clearHole();
                target.setTutz(true);
                currentPlayer.setTutz(holeNumber);
                target.setOwner(currentPlayer);
            } else if (target.size() > 3){
            ArrayList<Korgool> tempInventory = target.getInventory();
            currentPlayer.addKazan(tempInventory);
            target.clearHole();
            }
        }
    }

    /**
     * Check whether a player can capture a hole, the conditions are:
     *
     * Player is NOT owner. The hole has more than two korgools inside. The hole size 
     * is even.
     *
     * @param target        hole being targeted for capture
     * @param currentPlayer current player
     * @return true if the player can capture the hole
     */
    public static boolean canCapture(Hole target, Player currentPlayer) {

        return target.getOwner() != currentPlayer && target.size() > 2 && (target.size()==3 || target.isEven());

    }

}
