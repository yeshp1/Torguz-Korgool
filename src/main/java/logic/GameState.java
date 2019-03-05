package logic;

import java.util.Arrays;

/**
 * To hold the state of one game
 *
 */
public class GameState {
    private int[] holesSizes;   //number of korgools in each hole
    private boolean[] holeOwner;    //true if belongs to whiteSide
    private boolean whiteSideTurn;  //who has the next turn on the board.
    private int whiteSideTutz;
    private int darkSideTutz;
    private boolean playerOnWhiteSide;
    private int whiteSideKazanSize;
    private int darkSideKazanSize;

    public GameState(int[] holesSizes, boolean[] holeOwner, boolean whiteSideTurn,
                     int whiteSideTutz, int darkSideTutz, boolean playerOnWhiteSide,
                     int whiteSideKazanSize, int darkSideKazanSize) {
        this.holesSizes = holesSizes;
        this.holeOwner = holeOwner;
        this.whiteSideTurn = whiteSideTurn;
        this.whiteSideTutz = whiteSideTutz;
        this.darkSideTutz = darkSideTutz;
        this.playerOnWhiteSide = playerOnWhiteSide;
        this.whiteSideKazanSize = whiteSideKazanSize;
        this.darkSideKazanSize = darkSideKazanSize;
    }

    public int[] getHolesSizes() {
        return holesSizes;
    }

    public boolean[] getHoleOwner() {
        return holeOwner;
    }

    public boolean isWhiteSideTurn() {
        return whiteSideTurn;
    }

    public int getWhiteSideTutz() {
        return whiteSideTutz;
    }

    public int getDarkSideTutz() {
        return darkSideTutz;
    }

    public boolean isPlayerOnWhiteSide() {
        return playerOnWhiteSide;
    }

    public int getWhiteSideKazanSize() {
        return whiteSideKazanSize;
    }

    public int getDarkSideKazanSize() {
        return darkSideKazanSize;
    }

    public void setHolesSizes(int[] holesSizes) {
        this.holesSizes = holesSizes;
    }

    public void setHoleOwner(boolean[] holeOwner) {
        this.holeOwner = holeOwner;
    }

    public void setWhiteSideTurn(boolean whiteSideTurn) {
        this.whiteSideTurn = whiteSideTurn;
    }

    public void setWhiteSideTutz(int whiteSideTutz) {
        this.whiteSideTutz = whiteSideTutz;
    }

    public void setDarkSideTutz(int darkSideTutz) {
        this.darkSideTutz = darkSideTutz;
    }

    public void setPlayerOnWhiteSide(boolean playerOnWhiteSide) {
        this.playerOnWhiteSide = playerOnWhiteSide;
    }

    public void setWhiteSideKazanSize(int whiteSideKazanSize) {
        this.whiteSideKazanSize = whiteSideKazanSize;
    }

    public void setDarkSideKazanSize(int darkSideKazanSize) {
        this.darkSideKazanSize = darkSideKazanSize;
    }

    @Override
    public String toString() {
        String holeSizesAsString = Arrays.toString(holesSizes).replaceAll(" ", "");
        String holeOwnersAsString = Arrays.toString(holeOwner).replaceAll(" ", "");
        return  holeSizesAsString.substring(1, holeSizesAsString.length()-1) +
                "," + holeOwnersAsString.substring(1, holeOwnersAsString.length()-1) +
                "," + whiteSideTurn +
                "," + whiteSideTutz +
                "," + darkSideTutz +
                "," + playerOnWhiteSide +
                "," + whiteSideKazanSize +
                "," + darkSideKazanSize;
    }
}
