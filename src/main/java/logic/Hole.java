package logic;

import java.util.ArrayList;

/***
 * A Hole class contains information pertaining to the korgools currently in play. It is meant to be
 * one of many holes on the game board owned by one player at any given time
 ***/

public class Hole {

    private ArrayList<Korgool> inventory;
    private boolean tutz;
    private Player owner;

    public Hole(Player owner) {
        inventory = new ArrayList<Korgool>(9);
        tutz = false;
        this.owner = owner;
        // instantiate Korgools in this hole
        for (int i = 0; i < 9; i++) {
            inventory.add(new Korgool());
        }
    }

    /**
     * Non default Hole, setting the size.
     * 
     * @param owner
     * @param korgoolSize - number of starting korgools.
     */
    public Hole(Player owner, int korgoolSize){
        inventory = new ArrayList<Korgool>(9);
        tutz = false;
        this.owner = owner;
        // instantiate Korgools in this hole
        for (int i = 0; i < korgoolSize; i++) {
            inventory.add(new Korgool());
        }
    }

    /**
     * Removes all Korgools from a hole then returns a new ArrayList of the hole's previous contents
     *
     * @return
     */
    public void clearHole() {
        this.inventory.clear();
    }

    // Setter functions

    /**
     * Change the tutz state of a hole
     *
     * @param tutz state to set
     */
    public void setTutz(boolean tutz) {
        this.tutz = tutz;
    }

    // Add a Korgool to the hole
    public void addKorgool(Korgool toAdd) {
        inventory.add(toAdd);
    }

    // Set the player who owns this hole
    public void setOwner(Player owner) {
        this.owner = owner;
    }

    // Getter functions
    public ArrayList<Korgool> getInventory() {
        return inventory;
    }

    /**
     * Is the hole currently a Tutz?
     *
     * @return true if the hole is a Tutz
     */
    public boolean isTutz() {
        return tutz;
    }

    /**
     * The number of Korgool's in the hole
     *
     * @return the number of Korgool's in the hole
     */
    public int size() {
        return inventory.size();
    }

    /**
     * Is the number of Korgool's even?
     *
     * @return true if even
     */
    public boolean isEven() {
        return inventory.size() % 2 == 0;
    }

    /**
     * Get the owner of the hole.
     * 
     * @return the owner of the hole
     */
    public Player getOwner() {
        return owner;
    }

}
