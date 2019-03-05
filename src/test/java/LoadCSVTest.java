import logic.*;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LoadCSVTest {

    GameLoader loader;

    @Before
    public final void setUp()
    {
        loader = new GameLoader();
    }

    @After
    public final void tearDown()
    {
        loader = null;
    }


    @Test   //hole size is correctly defined in the board
    public void LoadFirstNonHeaderLine(){
        String expectedResult = "9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,true,true,true,true,true,true,true,true,true,false,false,false,false,false,false,false,false,false,true,-1,-1,true,0,0";
        String result = loader.load().get(0).toString();
        assertEquals(expectedResult,result);
    }

}
