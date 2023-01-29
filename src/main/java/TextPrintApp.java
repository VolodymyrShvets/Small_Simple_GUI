import javax.swing.*;
import java.awt.*;

public class TextPrintApp extends JFrame {
    private int APP_WIDTH = 600;
    private int APP_HEIGHT = 300;
    private int COMPONENT_HEIGHT = 30;
    private int COMPONENT_WIDTH = 150;
    private int LEFT_X = 25;
    private int RIGHT_X = 210;
    private JPanel controlPanel;
    private JPanel printPanel;
    private JTextField textField;
    private JSpinner fontSpinner;
    private JSpinner colorSpinner;

    public TextPrintApp() {
        init();
    }

    private void init() {
        setTitle("Text Print App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setSize(APP_WIDTH, APP_HEIGHT);
        controlPanel = new JPanel();
        setContentPane(controlPanel);
        controlPanel.setLayout(null);

        initComponents();

        printPanel = new JPanel();
        printPanel.setBounds(0, 150, APP_WIDTH, APP_HEIGHT);
        add(printPanel);

        setVisible(true);
    }

    private void initComponents() {
        JLabel textLabel = new JLabel("Текст:");
        textLabel.setBounds(LEFT_X, 10, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        controlPanel.add(textLabel);
        textField = new JTextField();
        textField.setBounds(LEFT_X, 35, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        textField.setColumns(100);
        textField.setName("inputTextField");
        controlPanel.add(textField);
        //------------------------------------------------------------------------
        JLabel fontLabel = new JLabel("Гарнітура:");
        fontLabel.setBounds(LEFT_X, 65, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        controlPanel.add(fontLabel);
        String[] fonts = new String[]{"Times New Roman", "Arial", "Verdana"};
        SpinnerModel fontsModel = new SpinnerListModel(fonts);
        fontSpinner = new JSpinner(fontsModel);
        fontSpinner.setBounds(LEFT_X, 90, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        fontSpinner.setName("fontSpinner");
        controlPanel.add(fontSpinner);
        //------------------------------------------------------------------------
        JLabel colorLabel = new JLabel("Колір:");
        colorLabel.setBounds(RIGHT_X, 10, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        controlPanel.add(colorLabel);
        String[] colors = new String[]{"Чорний", "Червоний", "Зелений", "Синій"};
        SpinnerModel colorsModel = new SpinnerListModel(colors);
        colorSpinner = new JSpinner(colorsModel);
        colorSpinner.setBounds(RIGHT_X, 35, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        colorSpinner.setName("colorSpinner");
        controlPanel.add(colorSpinner);
        //------------------------------------------------------------------------
        JButton button = new JButton("Вивести рядок");
        button.addActionListener(e -> {
            String message = textField.getText();
            String font = (String) fontSpinner.getValue();
            String color = (String) colorSpinner.getValue();

            printGivenString(message, font, color);
            //String result = String.format("Message: %s,\nFont: %s,\nColor: %s.\n", message, font, color);
            //System.out.print(result);
        });
        button.setBounds(RIGHT_X, 90, COMPONENT_WIDTH, COMPONENT_HEIGHT);
        button.setName("printTextButton");
        controlPanel.add(button);
    }

    private void printGivenString(String text, String font, String color) {
        Graphics g = printPanel.getGraphics();
        Color c = new Color(238, 238, 238);
        g.setColor(c);
        g.fillRect(0, 0, printPanel.getWidth(), printPanel.getHeight());

        Color chosenColor = Color.BLACK;
        Font chosenFont;

        switch (color) {
            case "Червоний":
                chosenColor = Color.RED;
                break;
            case "Зелений":
                chosenColor = Color.GREEN;
                break;
            case "Синій":
                chosenColor = Color.BLUE;
                break;
        }

        switch (font) {
            case "Arial": {
                chosenFont = new Font("Arial", Font.BOLD, 30);
                break;
            }
            case "Verdana": {
                chosenFont = new Font("Verdana", Font.BOLD, 30);
                break;
            }
            default:
                chosenFont = new Font("Times New Roman", Font.BOLD, 30);
        }

        g.setColor(chosenColor);
        g.setFont(chosenFont);

        g.drawString(text, 20, 40);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TextPrintApp frame = new TextPrintApp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
