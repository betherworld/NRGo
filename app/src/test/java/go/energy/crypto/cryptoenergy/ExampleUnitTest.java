package go.energy.crypto.cryptoenergy;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void elementCreation_isCorrect() throws ExecutionException, InterruptedException {
        String tmpUserId = "0x16663Fe2aB68e1434A472470a872421bDa4dEaA3";
        long dnaWater = 3;
        long dnaElectricity = 4;

        DatabaseHelper.createItem(tmpUserId, dnaWater, dnaElectricity);
        Element element = DatabaseHelper.getItem(tmpUserId);
        System.out.println(element);
    }
}