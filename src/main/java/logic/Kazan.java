package logic;

import java.util.ArrayList;

/**
 * This is a class that holds the Kazan 'container' of the game. It sums all the korgools owned by
 * the the player's respective holes and stores them.
 *
 * @version 2018.11.15
 * @authors Yeshvanth Prabakar, Jay Macpherson, Mikes, Tom,
 */
public class Kazan {

    //private ArrayList<Korgool> storedKorgools; atm its not used
    private int numberOfKorgools;

    public Kazan() {
        numberOfKorgools = 0;
    }

    /**
     * Non default game kazan starting size.
     * @param startingSize number of korgools
     */
    public Kazan(int startingSize) {
        numberOfKorgools = startingSize;


    }

    /**
     * The amount of Korgool's stored in the Kazan and the players holes.
     *
     * @return the number of korgools
     */
    public int size() {
        return numberOfKorgools;
    }


    /**
     * Adds an ArrayList of Korgool's to the player's Kazan.
     *
     * @param toAdd the number of the korgools
     */
    public void addKorgools(ArrayList<Korgool> toAdd) {
        numberOfKorgools += toAdd.size();
    }
}
