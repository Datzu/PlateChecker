package plate.checker;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PlateCheckerTest {

    @Test
    public void processPlate() {
        String[] plates = {
                "1234BBB",
                "9999GHJ",
                "9999BBB",
                "9999ZZZ",
                "7777SYX",
                "0000BBB"
        };
        String[] expectedPlates = {
                "1235BBB",
                "0000HHJ",
                "0000CBB",
                "0000BBB",
                "7778SYX",
                "0001BBB"
        };

        PlateChecker plateChecker = new PlateChecker();

        for (int i = 0; i < plates.length; i++) {
            assertTrue(expectedPlates[i].equals(plateChecker.processPlate(plates[i])));
            System.out.println("Plate " + plates[i] + " (" + expectedPlates[i] + ") is correct.");
        }
    }
}