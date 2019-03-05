package logic;

import java.util.ArrayList;

/***
 * The Game Board - responsible for holding the collection of holes.. implementing the moves that
 * take place.
 *
 */
public class Board {

    private Hole[] holes;
    private boolean whiteSideTurn;
    private static Capture capture;

    /**
     * Creates a board with the default game settings.
     *
     * @param player1 - player one
     * @param player2 - player two
     */
    public Board(Player player1, Player player2) {
        whiteSideTurn = true;
        holes = new Hole[18];
        this.capture = new Capture();
        int holesPerPlayer = holes.length / 2;
        // initialise each hole in holes, passing the player who owns the hole.
        for (int i = 0; i < holesPerPlayer; i++) {
            holes[i] = new Hole(player1);
        }
        for (int i = holesPerPlayer; i < (holes.length); i++) {
            holes[i] = new Hole(player2);
        }
    }

    //TODO: players, must set there non default settings before being passed.
    /**
     * Non default game settings.
     * @param player1 - player on white side
     * @param player2 - player on darkside
     * @param holesSizes - array of int, storing the size of each hole.
     * @param whiteSideTurn - true if its the white side turn.
     */
    public Board(Player player1, Player player2, int[] holesSizes, boolean[] holeOwners, boolean whiteSideTurn){
        this.whiteSideTurn = whiteSideTurn;
        holes = new Hole[18];
        this.capture = new Capture();
        int holesPerPlayer = holes.length / 2;
        // initialise each hole in holes, passing the player who owns the hole.
        for(int i = 0; i < holes.length; i ++){
            if(holeOwners[i]){  //white side...
                holes[i] = new Hole(player1,holesSizes[i]);
            }else{
                holes[i] = new Hole(player2, holesSizes[i]);
            }
        }


    }

    /**
     * TODO test case
     * Check if the hole selected is valid.
     * @param player - player making the move
     * @param holeIndex - index of the hole selected.
     * @return - true if hole belongs to player, otherwise false
     */
    public boolean validHoleSelected(Player player, int holeIndex){
        return getHolePlayerByIndex(holeIndex).equals(player) && (holes[holeIndex].size() > 0);
    }


    /**
     * @param currentPlayer - the player making the move
     * @param indexSelected - the Index of the hole selected.
     */
    public void move(int indexSelected, Player currentPlayer) {
        int numberOfKorgoolsToMove = holes[indexSelected].size(); // number of korgools in this hole
        redistribute(indexSelected, numberOfKorgoolsToMove);

        int indexOfLastKorgool = (indexSelected + numberOfKorgoolsToMove -1 ) % holes.length ;

        // Specifically for finding out if the hole opposite the current one belongs to the
        // other player as a tutz

        Hole opposite = holes[(indexOfLastKorgool + holes.length/2) % holes.length];
        // Attempt to capture korgools and claim tutz if eligible.
        capture.captureKorgools(holes[indexOfLastKorgool], currentPlayer, !opposite.isTutz(), indexOfLastKorgool);
        updatePlayerTurn();
    }


    /**
     * Update which side needs to make the next move.
     */
    public void updatePlayerTurn() {
        whiteSideTurn = !whiteSideTurn;
    }

    /**
     * Redistribute the kargools of index selected in an anticlockwise fashion
     *
     * @param indexSelected          - index in the holes array
     * @param kargoolsToRedistribute - number of korgools to redistribute.
     */
    public void redistribute(int indexSelected, int kargoolsToRedistribute) {
        // this should be an illegal move, not a wasted one?
        if (kargoolsToRedistribute == 0) {
            return;
        }
        ArrayList<Korgool> korgools = holes[indexSelected].getInventory();
        //holes[indexSelected].clearHole();
        if (kargoolsToRedistribute == 1) {
            holes[(indexSelected + 1) % holes.length].addKorgool(korgools.remove(0));
        } else {
            // redistribute in anticlockwise fashion...
            for (int i = indexSelected; i < (kargoolsToRedistribute + indexSelected); i++) {
                holes[(i % holes.length)].addKorgool(korgools.remove(0));
            }
        }

    }

    /**
     * get the holes array
     * @return - holes
     */
    public Hole[] getHoles(){
        return holes;
    }

    /**
     * return the hole size at index i
     * @param i - index in array
     * @return holes[i] size
     */
    public int getHoleSizeByIndex(int i){
        return holes[i].size();
    }
    /**
     * return the hole player at index i
     * @param i - index in array
     * @return holes[i] owner
     */
    public Player getHolePlayerByIndex(int i){
        return holes[i].getOwner();
    }

    /**
     * return true if it is the turn of the white side.
     * @return - whiteSideTurn
     */
    public boolean getWhiteSideTurn(){
        return whiteSideTurn;
    }

    /**
     * Return the number of Korgools in each hole.
     * @return - int[]
     */
    public int[] getHoleSizesArray(){
        int[] holeSizes = new int[18];
        for(int i = 0; i < holes.length ; i++){
            holeSizes[i] = getHoleSizeByIndex(i);
        }
        return holeSizes;
    }

    /**
     * get a array of hole owners.
     * True if hole belongs to whiteSide player, otherwise false.
     * @return
     */
    public boolean[] getHoleOwners(){
        boolean[] owners = new boolean[18];
        for(int i =0; i < holes.length; i++){
            if(getHolePlayerByIndex(i).getSide()){
                owners[i] = true;
            }else{
                owners[i] = false;
            }
        }
        return owners;
    }

    /**
     * Set the hole, referenced by index as a Tutz of player.
      * @param player - owner of the hole
     * @param index - index to be set as a tutz.
     */
    public void setHoleIndexAsTutz(int index){
        if(index!=-1) {
            holes[index].setTutz(true);
        }
    }


}
