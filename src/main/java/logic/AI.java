package logic;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.ArrayList;;

public class AI {

    private boolean whiteSide;
    private Player player;

    public AI(Player player) {
        this.player = player;
        this.whiteSide = player.getSide();
    }

    /**
     * Return the index of the hole to enact a move
     *
     * @return the index of the hole
     */
    public int getRandomMove(Hole[] holesToCheck) {

        ArrayList<Integer> holes = new ArrayList<Integer>();
        for (int i = 0; i < holesToCheck.length; i++) {

            if (holesToCheck[i].getOwner() == this.player && holesToCheck[i].size() > 0 ) {
                holes.add(i);
            }
        }

        int randomNum = ThreadLocalRandom.current().nextInt(0, holes.size());

        // will make more sense when the AI can "reroll" a turn that lands
        // on a tutz
        if (validMove(randomNum)) {
            return randomNum;
        }
        

        return randomNum;
    }



    /**
     * Check that the move is valid (i.e. the ai owns the hole.)
     *
     * TODO: Add tutz checking and "rerolling" if the move is on a Tutz
     *
     * @param toCheck
     * @return was the hole a valid choice
     */
    private boolean validMove(int toCheck) {
        if (this.whiteSide) {
            return ((0 <= toCheck) && (toCheck <= 8)) ? true : false;
        } else {
            return ((9 <= toCheck) && (toCheck <= 18)) ? true : false;
        }
    }

    /**
     * Get the player object for the AI
     *
     * @return the player
     */
    public Player getPlayer() {
        return this.player;
    }

    private HashMap<Integer, Integer> possibleMoves(Hole[] holesToCheck) {
        HashMap<Integer, Integer> results = new HashMap<Integer, Integer>();

        for (int i = 0; i < holesToCheck.length; i++) {
            if (holesToCheck[i].getOwner() == this.player) {
                // find how many korgools will be claimed on this move and add to results
                int endHole = (i + holesToCheck[i].size()) % holesToCheck.length;

                // 3
                results.put(i, korgoolYield(holesToCheck[endHole]));
                System.out.printf("Hole: %s%n || Yield: %s%n", i,  korgoolYield(holesToCheck[endHole]));
            }
        }

        return results;
    }

    /**
     * Calculate the number of Korgools that move
     * would capture; if the size of the hole is even
     * after the move, then all Korgools in that hole 
     * are captured, else, zero is returned.
     * 
     * @param hole
     * @return
     */
    private int korgoolYield(Hole hole)
    {
        if ((hole.size() + 1) % 2 == 0){
            return hole.size()+1;
        } else if ((hole.size() +1) ==3){
            return hole.size()+1;
        }
        return 0;
    }

    /**
     * Gets the best move by finding the highest
     * value in the results HashMap, then returns
     * the associated key.
     * 
     * @param moves - all the moves that can be made this turn
     * @return the key of the move yielding the most Korgools
     */
    private int optimalTurn(HashMap<Integer, Integer> moves) {
        Entry<Integer, Integer> max = null;
        for (Entry<Integer, Integer> entry : moves.entrySet()) {
            if (max == null || entry.getValue() > max.getValue()) {
                max = entry;
            }
            if (entry.getValue() == 3){
                return entry.getKey();
            }
        }


        return max.getKey();
    }

    /**
     * Calculates the possible moves and returns the 
     * move which yields the most Korgools
     * 
     * @param holesToCheck
     * @return the hole that yields the most Korgools
     */
    public int getBestMove(Hole[] holesToCheck) {
        HashMap<Integer, Integer> moves = possibleMoves(holesToCheck);
        if (moves.size() == 0)
        {
            return getRandomMove(holesToCheck);
        }
        int optimalTurn = optimalTurn(moves);

        return optimalTurn;
    }


}

