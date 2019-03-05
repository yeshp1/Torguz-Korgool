package logic;

import java.util.ArrayList;

/**
 * Player represents a player in the traditional game. A player has a Kazan and
 * a name, with (more to be implemented) soon.
 */
public class Player {
    private Kazan kazan;
    private String name;
    private int tutz; // -1 represents no Tutz
    private boolean whiteSide;

    public Player() {
        this.name = "Default Name";
        this.tutz = -1;
        this.kazan = new Kazan();
    }
    /**
     * Non-default constructor to pass a name
     * @param name
     */
    public Player(String name) {
        this.name = name;
        this.tutz = -1;
        this.kazan = new Kazan();
    }

    /**
     * Non default Player Constructor.
     * @param name name to set
     * @param startingKazanSize preload number of owned korgools
     * @param tutzStatus does this player own a tutz
     */
    public Player(int startingKazanSize, int tutzStatus) {
        this.tutz = tutzStatus;
        this.kazan = new Kazan(startingKazanSize);
    }

    /**
     * set the side of the player
     * @param whiteSide - pass true if on white side
     */
    public void setSide(boolean whiteSide){
        this.whiteSide = whiteSide;
    }

    /**
     *
     * @return True if on white side.
     */
    public boolean getSide(){
        return whiteSide;
    }



    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the Kazan object of the player
     *
     * @return the Kazan object
     */
    public Kazan getKazan() {
        return kazan;
    }

    /**
     * The number of Korgool's in the player's Kazan
     *
     * @return number of Korgool's in Kazan
     */
    public int getKazanCount() {
        return this.kazan.size();
    }

    /**
     * Add Korgool's to the Kazan
     *
     * @param toAdd number of Korgools to add
     */
    public void addKazan(ArrayList<Korgool>  toAdd) {
        kazan.addKorgools(toAdd);
    }


    /**
     * Set the player Tutz to a given state
     *
     */
    public void setTutz(int toSet) {
        this.tutz = toSet;
    }

    /**
     * Get the index of the player's Tutz,
     * -1 represents no Tutz
     *
     * @return the index of the player's Tutz
     */
    public int getTutz() {
        return this.tutz;
    }

    /**
     * Does the player have a tutz?
     *
     * @return true if the player has a tutz
     */
    public boolean hasTutz(){
        return this.tutz != -1 ;
    }




}
