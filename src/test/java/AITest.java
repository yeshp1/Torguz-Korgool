import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.nio.file.FileAlreadyExistsException;
import org.junit.Test;
import logic.*;

public class AITest{

    // @Test
    // public void getHoleMoveWhiteSide(){
    //     Player whiteSide = new Player();
    //     whiteSide.setSide(true);
    //     AI ai = new AI(whiteSide);
    //     int toCheck = ai.getRandomMove();

    //     assertTrue(0<= toCheck && toCheck <= 9);
    // }

    // @Test
    // public void getHoleMoveBlackSide(){
    //     Player blackSide = new Player();
    //     blackSide.setSide(false);
    //     AI ai = new AI(blackSide);
    //     int toCheck = ai.getRandomMove();

    //     assertTrue(10<= toCheck && toCheck <= 18);
    // }

    @Test
    public void bestMoveShouldReturn3()
    {
        Player blackSide = new Player();
        Player whiteSide = new Player();
        blackSide.setSide(false);
        whiteSide.setSide(true);
        AI ai = new AI(whiteSide);

        Hole[] holes = new Hole[18];
        int holesPerPlayer = holes.length / 2;
        for (int i = 0; i < holesPerPlayer; i++) {
            holes[i] = new Hole(whiteSide);
            holes[i].clearHole();
            holes[i].addKorgool(new Korgool());
        }
        for (int i = holesPerPlayer; i < (holes.length); i++) {
            holes[i] = new Hole(blackSide);
        }

        // make hole 12 only hole that will be favoured
        holes[12].addKorgool(new Korgool());
        holes[12].addKorgool(new Korgool());
        holes[12].addKorgool(new Korgool());
        holes[12].addKorgool(new Korgool());


        // make it so hole 3 is chosen, by placing 9
        holes[3].clearHole();
        holes[3].addKorgool(new Korgool());
        holes[3].addKorgool(new Korgool());
        holes[3].addKorgool(new Korgool());
        holes[3].addKorgool(new Korgool());
        holes[3].addKorgool(new Korgool());
        holes[3].addKorgool(new Korgool());
        holes[3].addKorgool(new Korgool());
        holes[3].addKorgool(new Korgool());
        holes[3].addKorgool(new Korgool());

        int toCheck = ai.getBestMove(holes);

        assertEquals(3, toCheck);

    }
    
    @Test
    public void bestMoveShouldReturn6WithTutz()
    {
        Player blackSide = new Player();
        Player whiteSide = new Player();
        blackSide.setSide(false);
        whiteSide.setSide(true);
        AI ai = new AI(whiteSide);

        Hole[] holes = new Hole[18];
        int holesPerPlayer = holes.length / 2;
        for (int i = 0; i < holesPerPlayer; i++) {
            holes[i] = new Hole(whiteSide);
            holes[i].clearHole();
            holes[i].addKorgool(new Korgool());
        }
        for (int i = holesPerPlayer; i < (holes.length); i++) {
            holes[i] = new Hole(blackSide);
        }

        // make hole 12 only hole that will be favoured
        holes[12].addKorgool(new Korgool());
        holes[12].addKorgool(new Korgool());
        holes[14].addKorgool(new Korgool());
        holes[14].addKorgool(new Korgool());
        holes[14].addKorgool(new Korgool());
        holes[14].addKorgool(new Korgool());
        holes[14].addKorgool(new Korgool());



        // make it so hole 3 is chosen, by placing 6
        holes[6].clearHole();
        holes[6].addKorgool(new Korgool());
        holes[6].addKorgool(new Korgool());
        holes[6].addKorgool(new Korgool());
        holes[6].addKorgool(new Korgool());
        holes[6].addKorgool(new Korgool());
        holes[6].addKorgool(new Korgool());

        int toCheck = ai.getBestMove(holes);

        assertEquals(6, toCheck);

    }

    
    @Test
    public void bestMoveShouldReturn6()
    {
        Player blackSide = new Player();
        Player whiteSide = new Player();
        blackSide.setSide(false);
        whiteSide.setSide(true);
        AI ai = new AI(whiteSide);

        Hole[] holes = new Hole[18];
        int holesPerPlayer = holes.length / 2;
        for (int i = 0; i < holesPerPlayer; i++) {
            holes[i] = new Hole(whiteSide);
            holes[i].clearHole();
            holes[i].addKorgool(new Korgool());
        }
        for (int i = holesPerPlayer; i < (holes.length); i++) {
            holes[i] = new Hole(blackSide);

        }

        // make hole 12 only hole that will be favoured
        holes[12].addKorgool(new Korgool());
        holes[12].addKorgool(new Korgool());
        holes[12].addKorgool(new Korgool());
        holes[12].addKorgool(new Korgool());

        // make it so hole 3 is chosen, by placing 69
        holes[6].addKorgool(new Korgool());
        holes[6].addKorgool(new Korgool());
        holes[6].addKorgool(new Korgool());
        holes[6].addKorgool(new Korgool());
        holes[6].addKorgool(new Korgool());
        holes[6].addKorgool(new Korgool());

        int toCheck = ai.getBestMove(holes);

        assertEquals(6, toCheck);

    }

    
}