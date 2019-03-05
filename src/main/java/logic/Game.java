package logic;
import gui.ViewManager;
import java.util.ArrayList;


/**
 * This is the main game class of the "Kazan "game.
 *
 * @version 2018.11.15
 * @authors Yeshvanth Prabakar, Jay Macpherson, Mikes, Tom,
 */
public class Game {
    private Boolean wantToQuit;
    private ViewManager gui;
    private Board board;
    private Player computer, player;
    private AI ai;
    private static final int FRAME_WIDTH = 1200;
    private static final int FRAME_HEIGHT = 900;
    private ArrayList<GameState> games; //stores a collection of the game states which could be loaded from.
    /**
     * Default Game Constructor, player start on white side.
     *
     */
    public Game() {
        wantToQuit=false;
        computer = new Player();
        player = new Player();
        player.setSide(true);
        computer.setSide(false);
        ai = new AI(computer);
        board = new Board(player, computer);
        games = new GameLoader().load();
        gui = new ViewManager(FRAME_WIDTH, FRAME_HEIGHT, this);
    }

    /**
     * TODO SET THROUGH GUI AND REMOVE FROM CONSTRUCTOR
     * set the side of the white player
     * @param whiteSide - true if starting side is white.
     */
    public void setSides(boolean whiteSide){
        player.setSide(whiteSide);
        computer.setSide(!whiteSide);
        if(!whiteSide) {
            player.setSide(false);
            computer.setSide(true);
            board = new Board(computer, player);
        }

        if(board.getWhiteSideTurn() && computer.getSide()){
            computerMove();
        }
        updateView();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Game game =new Game();
    }


    /**
     * Make the player move, on the action of player selecting a hole that belongs.
     * @param holeIndex - hole selected to move
     * @return true if successful move else false.
     */
    public boolean playerMove(int holeIndex){
        if(board.validHoleSelected(player, holeIndex) && (player.getSide() == board.getWhiteSideTurn())){
            board.move(holeIndex, player);
            return true;
        }
        return false;
    }

    /**
     * TODO - temp set as 15
     * TODO AI code will go here
     * Ai makes a move on the board
     */
    public void computerMove(){
        int holeIndex = ai.getBestMove(board.getHoles());
        if (board.validHoleSelected(computer, holeIndex) && (computer.getSide() == board.getWhiteSideTurn()))
        {
            System.out.println("Computer mooved index " + holeIndex);
            board.move(holeIndex, computer);
        }
    }


    /**
     * the hole selected by the player
     * @param playerSelectedHole
     */
    public void play(int playerSelectedHole) {

        System.out.println("////////////////////////////////");
        System.out.println("hole " + (playerSelectedHole+1) + " has been selected");
        System.out.println("================================");
        if (playerMove(playerSelectedHole)) {
            if (!isFinished()) {
                System.out.println("Computer Move....");
                computerMove();
            }
            updateView();
        }


        for (int x : board.getHoleSizesArray()){
            System.out.print(x + ", ");
        }
    }

    /**
     * Values passed to update game gui
     */
    public void updateView(){
        gui.getGameView().updateView(board.getHoleSizesArray(),
                     player.getTutz(), player.getKazanCount(), player.getSide(),
                     computer.getTutz(), computer.getKazanCount(), computer.getSide(),
                     board.getWhiteSideTurn(), hasWon(), isDraw(), hasLost()
                      );
    }

    /**
     * If player wants to quit in between game
     */
    public Boolean getWantToQuit() {
        // if player wants to quit the game
        return wantToQuit;
    }

    /**
     * @param wantToQuit
     * sets the quit condition
     */
    public void setWantToQuit(boolean wantToQuit) {
        // if player wants to quit the game
        this.wantToQuit = wantToQuit;
    }

    /**
     * END conditions for the game
     * @return - true if game conditions are satisfied.
     */
    public Boolean endConditions() {
        return hasWon() || isDraw() || hasLost();
        // time based win constraint if implemented?
    }

    /**
     * Win condition
     * @return true if game has won.
     */
    public Boolean hasWon()
    {
        return player.getKazanCount() >= 82;
    }

    /**
     * Draw conditions for the game
     * @return - true if game has ended as draw
     */
    public Boolean isDraw()
    {
        return player.getKazanCount() == 81 && computer.getKazanCount() == 81;
    }

    /**
     * Losing conditions for the game
     * @return - true if player has lost
     */
    public Boolean hasLost()
    {
        return computer.getKazanCount()>=82;
    }


    /**
     * * Determines the game loop
     * @return - true if game has finished
     */
    public Boolean isFinished() {
        return (endConditions() || getWantToQuit());
    }


    /**
     * will retrieve the game state from the games collection
     * update the status of the board.
     * update the view.
     * @param gameIndex - selected from the gui.
     */
    public void loadGame(int gameIndex){
        GameState gameToSet = games.get(gameIndex);
        //set board and player accordingly
        if(gameToSet.isPlayerOnWhiteSide()){
            player = new Player(gameToSet.getWhiteSideKazanSize(),gameToSet.getWhiteSideTutz());
            player.setSide(true);
            player.setTutz(gameToSet.getWhiteSideTutz());
            computer = new Player(gameToSet.getDarkSideKazanSize(), gameToSet.getDarkSideTutz());
            computer.setSide(false);
            computer.setTutz(gameToSet.getDarkSideTutz());
            board = new Board(player, computer, gameToSet.getHolesSizes(), gameToSet.getHoleOwner(),gameToSet.isWhiteSideTurn());
        }else{
            player = new Player(gameToSet.getDarkSideKazanSize(),gameToSet.getDarkSideTutz());
            player.setSide(false);
            player.setTutz(gameToSet.getDarkSideTutz());
            computer = new Player(gameToSet.getWhiteSideKazanSize(), gameToSet.getWhiteSideTutz());
            computer.setSide(true);
            computer.setTutz(gameToSet.getWhiteSideTutz());
            board = new Board(computer, player, gameToSet.getHolesSizes(), gameToSet.getHoleOwner(),gameToSet.isWhiteSideTurn());
        }
        ai = new AI(computer);
        //set tutz.
        board.setHoleIndexAsTutz(gameToSet.getWhiteSideTutz());
        board.setHoleIndexAsTutz(gameToSet.getDarkSideTutz());
        System.out.println(gameToSet.toString());
        computerMove();
        updateView();

    }

    /**
     * write to file and add to in game games list too
     */
    public void saveCurrentGameToCSV(){
        GameState currentGame;
        if(player.getSide()) {
            currentGame = new GameState(board.getHoleSizesArray(), board.getHoleOwners(), board.getWhiteSideTurn(),
                    player.getTutz(), computer.getTutz(), true,
                    player.getKazanCount(), computer.getKazanCount());
        }else{
            currentGame = new GameState(board.getHoleSizesArray(), board.getHoleOwners(), board.getWhiteSideTurn(),
                    computer.getTutz(), player.getTutz(), false,
                    computer.getKazanCount(), player.getKazanCount());
        }
        games.add(currentGame);
        System.out.println(currentGame.toString());
        new GameSaver().writeToFile(currentGame.toString());
    }


}
