package logic;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;

import com.opencsv.CSVReader;

import java.net.URISyntaxException;

public class GameLoader {

    public GameLoader(){}

    /**
     * Return an ArrayList containing the rows in the Games csv file..
     * Utilises the opencsv dependancy, and CSVReader class.
     */
    public ArrayList<GameState> load() {
        String autosavePath = "src/main/resources/GameStates.csv";
        ArrayList<GameState> games = new ArrayList<GameState>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(autosavePath), "UTF-8"));
            CSVReader reader = new CSVReader(bufferedReader);
            String [] line;
            //skip the first row (column headers)
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                int[] holeSizes = new int[18];
                boolean[] holeOwner = new boolean[18];
                for(int i = 0; i < holeSizes.length; i++){
                    holeSizes[i] = convertStringToInt(line[i]); //convert to int
                }
                for(int i = 0; i < holeOwner.length; i++){
                    holeOwner[i] = convertToBoolean(line[i+18]);   //convert to boolean
                }
                //index is now 36
                boolean whiteSideTurn = convertToBoolean(line[36]);  //who has the next turn on the board.
                int whiteSideTutz = convertStringToInt(line[37]);
                int darkSideTutz = convertStringToInt(line[38]);
                boolean playerOnWhiteSide = convertToBoolean(line[39]);
                int whiteSideKazanSize = convertStringToInt(line[40]);
                int darkSideKazanSize = convertStringToInt(line[41]);

                GameState game = new GameState(holeSizes,holeOwner,whiteSideTurn,
                        whiteSideTutz,darkSideTutz,playerOnWhiteSide,
                        whiteSideKazanSize,darkSideKazanSize );
                games.add(game);
            }
        } catch(IOException e){
            System.out.println();
            e.printStackTrace();
        }
        return games;
    }

    /**
     * Convert the string to a boolean value.
     * @param trueOrFalse
     * @return
     */
    private boolean convertToBoolean(String trueOrFalse){
        if(trueOrFalse.trim().equals("true")){
            return true;
        }else{ return false;}
    }

    /**
     * Convert the string passed to an integer.
     * trim handles any white space issues.
     * @param value
     * @return
     */
    private int convertStringToInt(String value){
        int intValue;
        if(value.trim().equals("")){
             intValue =  -1;
        }else{
             intValue = Integer.parseInt(value);
        }
        return intValue;
    }

}
