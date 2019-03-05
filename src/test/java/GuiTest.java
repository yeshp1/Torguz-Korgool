import gui.GameView;
import gui.ViewManager;
import gui.WelcomeView;
import static org.junit.Assert.assertEquals;
import java.awt.Window;
import static org.junit.Assert.assertTrue;
import javax.swing.*;
import logic.Game;
import org.junit.Before;
import org.junit.Test;
import com.athaydes.automaton.Swinger;
import java.util.Random;

public class GuiTest {

    @Test
    public void test()
    {
        new Game();
        testDefaultKazanSize();
        testFirstMoveKazanSize();
        testFirstMoveKorgools();
    }

    public void testDefaultKazanSize(){
        Swinger swinger = Swinger.forSwingWindow();
        swinger.pause(300)
                .clickOn("name:startGame")
                .pause(300);
        swinger = Swinger.forSwingWindow();
        JLabel computerCount = (JLabel) swinger.getAt("name:computerKazan");
        int computerCountInt = Integer.parseInt(computerCount.getText());
        JLabel playerCount = (JLabel) swinger.getAt("name:playerKazan");
        int playerCountInt = Integer.parseInt(playerCount.getText());
        assertEquals(0, playerCountInt);
        assertEquals(0, computerCountInt);
    }

    public void testFirstMoveKazanSize(){
        Swinger swinger = Swinger.forSwingWindow();
        swinger.clickOn("name:hole1")
                .pause(300);
        JLabel computerCount = (JLabel) swinger.getAt("name:computerKazan");
        int computerCountInt = Integer.parseInt(computerCount.getText());
        JLabel playerCount = (JLabel) swinger.getAt("name:playerKazan");
        int playerCountInt = Integer.parseInt(playerCount.getText());
        assertEquals(0, playerCountInt);
        assertEquals(0, computerCountInt);
    }

    public void testFirstMoveKorgools() {
        Swinger swinger = Swinger.forSwingWindow();
        //checking all of players korgools for first move
        JButton button = (JButton) swinger.getAt("name:hole1");
        assertEquals("1", button.getText());
        //first move remaining holes
        for (int i = 2; i < 10; i++) {
            JButton button2 = (JButton) swinger.getAt("name:hole" + i);
            assertEquals("10", button2.getText());
        }
        //checking all of opponents korgools for first move
        for (int i = 10; i < 17; i++) {
            JButton button3 = (JButton) swinger.getAt("name:hole" + i);
            if (i == 10) {
                assertEquals("1", button3.getText());
            } else {
                assertEquals("10", button3.getText());
            }
        }


        //further moves
        for (int i = 2; i < 10; i++) {
            swinger = Swinger.forSwingWindow();
            swinger.clickOn("name:hole" + i)
                    .pause(900);
        }
    }

    //testing for finishing conditions
    public void FinishedConditions()
    {
        Random rand = new Random();
        Game game = new Game();
        Swinger swinger = Swinger.forSwingWindow();
        swinger.pause(300)
                .clickOn("name:startGame")
                .pause(300);

        swinger = Swinger.forSwingWindow();
        swinger.clickOn("name:hole9");

        JLabel computerCount = (JLabel) swinger.getAt("name:computerKazan");
        int computerCountInt = Integer.parseInt(computerCount.getText());
        JLabel playerCount = (JLabel) swinger.getAt("name:playerKazan");
        int playerCountInt = Integer.parseInt(playerCount.getText());

        //keep looping till game ends
        while(!(computerCountInt>=81) && !(playerCountInt>=81)) {
            swinger.pause(300);
            //select random hole
            int i = rand.nextInt(9)+1;
            swinger.clickOn("name:hole" + i)
                    .pause(300);
            computerCount = (JLabel) swinger.getAt("name:computerKazan");
            computerCountInt = Integer.parseInt(computerCount.getText());
            playerCount = (JLabel) swinger.getAt("name:playerKazan");
            playerCountInt = Integer.parseInt(playerCount.getText());
        }
    }
}
