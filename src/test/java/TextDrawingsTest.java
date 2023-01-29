import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.fixture.JRadioButtonFixture;
import org.assertj.swing.fixture.JTextComponentFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TextDrawingsTest extends AssertJSwingJUnitTestCase {
    private FrameFixture window;
    private TextDrawingsApp frame;

    @Override
    protected void onSetUp() {
        frame = GuiActionRunner.execute(() -> new TextDrawingsApp());
        window = new FrameFixture(robot(), frame);
    }

    @Test
    public void test() {
        String actualText = "Never trust nobody.";

        JTextComponentFixture textField = window.textBox("inputTextField");
        assertNotNull(textField);
        textField.enterText(actualText);

        JRadioButtonFixture radioFontSize = window.radioButton("size14px").check().check().check();
        assertNotNull(radioFontSize);
        assertEquals(14, frame.fontSize);

        assertEquals("", frame.previousText);

        JButtonFixture printButton = window.button("printTextButton");
        assertNotNull(printButton);
        printButton.click();

        assertEquals(actualText, frame.previousText);
    }
}
