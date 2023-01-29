import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class MovingShapesTest {
    @Test
    public void getColorTest1() {
        Color expected = Color.BLACK;
        Color actual = MovingShapesApp.getColor("");

        assertEquals(expected, actual);
    }

    @Test
    public void getColorTest2() {
        Color expected = Color.RED;
        Color actual = MovingShapesApp.getColor("Червоний");

        assertEquals(expected, actual);
    }

    @Test
    public void getColorTest3() {
        Color expected = Color.GREEN;
        Color actual = MovingShapesApp.getColor("Зелений");

        assertEquals(expected, actual);
    }

    @Test
    public void getColorTest4() {
        Color expected = Color.BLUE;
        Color actual = MovingShapesApp.getColor("Синій");

        assertEquals(expected, actual);
    }
}
