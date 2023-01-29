import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DrawingShapesTest {
    DrawingShapesApp app;

    @Before
    public void setUp() {
        app = new DrawingShapesApp();
    }

    @Test
    public void test() {
        app.drawShape(1);

        assertNotNull(app.square);
        assertTrue(app.squareS);

        app.drawShape(2);

        assertNotNull(app.oval);
        assertTrue(app.circleS);

        app.drawShape(3);

        assertNotNull(app.triangle);
        assertTrue(app.triangleS);
    }
}
