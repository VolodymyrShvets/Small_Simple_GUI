import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TextDrawingsApp extends JFrame {
    private int APP_WIDTH = 800;
    private int APP_HEIGHT = 800;
    private JPanel upper;
    private JPanel lower;
    private JTextField textX;
    private JTextField textY;
    JTextField textField;
    Color color;
    int fontSize;
    int x;
    int y;
    String previousText;

    public TextDrawingsApp() {
        init();
    }

    private void init() {
        color = Color.BLACK;
        fontSize = 12;
        x = y = 0;
        previousText = "";

        setTitle("Text Drawings App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(APP_WIDTH, APP_HEIGHT);

        Container c = getContentPane();

        lower = new JPanel();
        lower.setBounds(0, 570, APP_WIDTH, APP_HEIGHT);
        lower.setLayout(null);

        upper = new JPanel();
        upper.setBounds(0, 0, APP_WIDTH, 570);
        upper.setLayout(null);
        upper.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                x = e.getX();
                y = e.getY();
                textX.setText(String.valueOf(x));
                textY.setText(String.valueOf(y));
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        c.add(lower);
        c.add(upper);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        JLabel parameters = new JLabel("Параметри напису:");
        parameters.setBounds(20, 20, 180, 30);
        lower.add(parameters);
        JLabel textLabel = new JLabel("Текст:");
        textLabel.setBounds(20, 50, 180, 30);
        lower.add(textLabel);
        textField = new JTextField();
        textField.setBounds(80, 50, 240, 30);
        textField.setColumns(100);
        textField.setName("inputTextField");
        lower.add(textField);

        JLabel labelColor = new JLabel("Колір:");
        labelColor.setBounds(340, 50, 50, 30);
        lower.add(labelColor);

        ButtonGroup colorGroup = new ButtonGroup();
        JRadioButton black = new JRadioButton("Чорний");
        black.addActionListener(e -> {
            color = Color.BLACK;
        });
        black.setBounds(380, 50, 70, 30);
        colorGroup.add(black);
        lower.add(black);
        black.setSelected(true);

        JRadioButton red = new JRadioButton("Червоний");
        red.addActionListener(e -> {
            color = Color.RED;
        });
        red.setBounds(470, 50, 90, 30);
        colorGroup.add(red);
        lower.add(red);

        JRadioButton green = new JRadioButton("Зелений");
        green.addActionListener(e -> {
            color = Color.GREEN;
        });
        green.setBounds(570, 50, 80, 30);
        colorGroup.add(green);
        lower.add(green);

        JRadioButton blue = new JRadioButton("Синій");
        blue.addActionListener(e -> {
            color = Color.BLUE;
        });
        blue.setBounds(660, 50, 80, 30);
        colorGroup.add(blue);
        lower.add(blue);

        JLabel labelSize = new JLabel("Розмір:");
        labelSize.setBounds(20, 90, 100, 30);
        lower.add(labelSize);
        ButtonGroup sizeGroup = new ButtonGroup();
        JRadioButton size10px = new JRadioButton("10pt");
        size10px.addActionListener(e -> {
            fontSize = 10;
        });
        size10px.setBounds(80, 90, 70, 30);
        sizeGroup.add(size10px);
        lower.add(size10px);

        JRadioButton size12px = new JRadioButton("12pt");
        size12px.addActionListener(e -> {
            fontSize = 12;
        });
        size12px.setBounds(160, 90, 70, 30);
        sizeGroup.add(size12px);
        lower.add(size12px);
        size12px.setSelected(true);

        JRadioButton size14px = new JRadioButton("14pt");
        size14px.addActionListener(e -> {
            fontSize = 14;
        });
        size14px.setBounds(240, 90, 70, 30);
        sizeGroup.add(size14px);
        size14px.setName("size14px");
        lower.add(size14px);

        JLabel coordinateX = new JLabel("Координата Х:");
        coordinateX.setBounds(20, 130, 150, 30);
        lower.add(coordinateX);
        textX = new JTextField();
        textX.setEditable(false);
        textX.setBounds(130, 130, 40, 30);
        textX.setText(String.valueOf(x));
        lower.add(textX);
        JLabel coordinateY = new JLabel("Координата Y:");
        coordinateY.setBounds(200, 130, 150, 30);
        lower.add(coordinateY);
        textY = new JTextField();
        textY.setEditable(false);
        textY.setBounds(310, 130, 40, 30);
        textY.setText(String.valueOf(y));
        lower.add(textY);

        JButton printButton = new JButton("Вивести рядок");
        printButton.setBounds((APP_WIDTH - 180) / 2 + 100, 130, 180, 30);
        printButton.addActionListener(e -> {
            printString();
        });
        printButton.setName("printTextButton");
        lower.add(printButton);
    }

    public void printString() {
        clearPanel();
        String str = textField.getText();

        Graphics g = upper.getGraphics();
        g.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
        g.setColor(color);
        g.drawString(str, x, y);

        previousText = str;
    }

    private void clearPanel() {
        Graphics g = upper.getGraphics();
        Color c = new Color(238, 238, 238);
        g.setColor(c);
        g.fillRect(0, 0, upper.getWidth(), 600);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new TextDrawingsApp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
