import org.example.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTest {
    @Test
    public void testAdd() {
        System.out.println("Testing add");
        assertEquals(5, Main.add(2, 3));
    }
}
