package logic;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Used for writing to the game states csv file.
 */
public class GameSaver {

    public GameSaver(){}

    public void writeToFile(String state){
        // you want to output to file
        String autosavePath = "src/main/resources/GameStates.csv";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(autosavePath, true));
            writer.newLine();
            writer.write(state);
            writer.close();
        }catch (IOException e){
            System.out.println(e);
        }
    }
}
