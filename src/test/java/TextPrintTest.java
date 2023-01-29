import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JSpinnerFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TextPrintTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;

    @Override
    protected void onSetUp() {
        TextPrintApp frame = GuiActionRunner.execute(() -> new TextPrintApp());
        window = new FrameFixture(robot(), frame);
        window.show();
    }

    @Override
    protected void onTearDown() {
        window.cleanUp();
    }

    @Test
    public void shouldBeShowInfodialog() {
        String expectedMessage = "War newer changes...";
        String expectedFont = "Arial";
        String expectedColor = "Зелений";

        JTextComponentFixture textField = window.textBox("inputTextField");
        assertNotNull(textField);
        textField.enterText(expectedMessage);
        String actualText = textField.text();

        JSpinnerFixture fontSpinner = window.spinner("fontSpinner");
        assertNotNull(fontSpinner);
        fontSpinner.increment(); // should be ARIAL
        String actualFont = fontSpinner.text();

        JSpinnerFixture colorSpinner = window.spinner("colorSpinner");
        assertNotNull(colorSpinner);
        colorSpinner.increment(2); // should be GREEN
        String actualColor = colorSpinner.text();

        JButtonFixture printButton = window.button("printTextButton");
        assertNotNull(printButton);
        printButton.click();

        String expected = String.format("Message: %s,\nFont: %s,\nColor: %s.\n", expectedMessage, expectedFont, expectedColor);
        String actual = String.format("Message: %s,\nFont: %s,\nColor: %s.\n", actualText, actualFont, actualColor);
        assertEquals(expected, actual);
    }
}
