/*import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

 public class SaveLoad {

    private static final String autosavePath = "../savedata/autosave.csv";


    public static List<String[]> LoadGames() throws IOException {

        String line = "";
        FileReader reader = new FileReader(autosavePath);
        BufferedReader csvReader = new BufferedReader(reader);
        List<String[]> gamefiles = new ArrayList<>();


        try {

            //skip first line header
            csvReader.readLine();

            while ((line = csvReader.readLine()) != null){

                String[] gamefile = line.split(",");
                gamefiles.add(gamefile);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (csvReader != null) {
                try {
                    csvReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return gamefiles;
    }

    public static void SaveGame(
            boolean whitesideTurn,
            Hole[] holes,
            Player player,
            Player ai

    ) throws IOException {

        try {
            FileWriter outputfile = new FileWriter(autosavePath,true);

            // create CSVWriter object filewriter object as parameter
            BufferedWriter writer = new BufferedWriter(outputfile);

            writer.append(String.valueOf(whitesideTurn))
                    .append(String.valueOf(holes[0].size())).append(String.valueOf(holes[1].size()))
                    .append(String.valueOf(holes[2].size())).append(String.valueOf(holes[3].size()))
                    .append(String.valueOf(holes[4].size())).append(String.valueOf(holes[5].size()))
                    .append(String.valueOf(holes[6].size())).append(String.valueOf(holes[7].size()))
                    .append(String.valueOf(holes[8].size())).append(String.valueOf(holes[9].size()))
                    .append(String.valueOf(holes[10].size())).append(String.valueOf(holes[11].size()))
                    .append(String.valueOf(holes[12].size())).append(String.valueOf(holes[13].size()))
                    .append(String.valueOf(holes[14].size())).append(String.valueOf(holes[15].size()))
                    .append(String.valueOf(holes[16].size())).append(String.valueOf(holes[17].size()))
                    .append(String.valueOf(holes[0].isTutz())).append(String.valueOf(holes[1].isTutz()))
                    .append(String.valueOf(holes[2].isTutz())).append(String.valueOf(holes[3].isTutz()))
                    .append(String.valueOf(holes[4].isTutz())).append(String.valueOf(holes[5].isTutz()))
                    .append(String.valueOf(holes[6].isTutz())).append(String.valueOf(holes[7].isTutz()))
                    .append(String.valueOf(holes[8].isTutz())).append(String.valueOf(holes[9].isTutz()))
                    .append(String.valueOf(holes[10].isTutz())).append(String.valueOf(holes[11].isTutz()))
                    .append(String.valueOf(holes[12].isTutz())).append(String.valueOf(holes[13].isTutz()))
                    .append(String.valueOf(holes[14].isTutz())).append(String.valueOf(holes[15].isTutz()))
                    .append(String.valueOf(holes[16].isTutz())).append(String.valueOf(holes[17].isTutz()))
                    .append(player.getName()).append(ai.getName())
                    .append(String.valueOf(player.getTutz())).append(String.valueOf(ai.getTutz()))
                    .append(String.valueOf(player.getKazanCount())).append(String.valueOf(player.getKazanCount()))
                    .append(String.valueOf(player.getSide())).append(String.valueOf(ai.getSide()));

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

*/