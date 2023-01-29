import javax.swing.*;
import java.awt.*;

public class MovingShapesApp extends JFrame {
    private int APP_WIDTH = 800;
    private int APP_HEIGHT = 800;
    private JPanel contentPane;
    private JPanel lower;
    private JPanel upper;
    private Color color;
    int width = 100;
    int height = 50;
    int x = (APP_WIDTH - width) / 2;
    int y = (APP_HEIGHT - height) / 2 - 50;

    public MovingShapesApp() {
        init();
    }

    private void init() {
        color = Color.BLACK;
        setTitle("Moving Shapes App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setSize(APP_WIDTH, APP_HEIGHT);
        contentPane = new JPanel();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        upper = new JPanel();
        upper.setBounds(0, 10, APP_WIDTH, 50);
        lower = new JPanel();
        lower.setBounds(0, 55, APP_WIDTH, APP_HEIGHT);

        initComponents();
        add(upper);
        add(lower);

        setVisible(true);
    }

    private void initComponents() {
        JButton newShape = new JButton("Виведення зображення");
        newShape.addActionListener(e -> {
            x = (APP_WIDTH - width) / 2;
            y = (APP_HEIGHT - height) / 2 - 50;
            printRectangle(color, x, y);
        });
        upper.add(newShape);

        JLabel colorLabel = new JLabel("Колір:");
        colorLabel.setBounds(0, 60, 50, 30);
        lower.add(colorLabel);
        String[] colors = new String[]{"Чорний", "Червоний", "Зелений", "Синій"};
        SpinnerModel colorsModel = new SpinnerListModel(colors);
        JSpinner colorSpinner = new JSpinner(colorsModel);
        colorSpinner.setBounds(0, 100, 200, 30);
        colorSpinner.addChangeListener(e -> {
            String colorValue = (String) colorSpinner.getValue();
            color = getColor(colorValue);
            printRectangle(color, x, y);
        });
        lower.add(colorSpinner);

        ButtonGroup group = new ButtonGroup();
        JRadioButton left = new JRadioButton("Вліво");
        left.addActionListener(e -> {
            x -= 10;
            printRectangle(color, x, y);
        });
        group.add(left);
        lower.add(left);
        JRadioButton up = new JRadioButton("Вгору");
        up.addActionListener(e -> {
            y -= 10;
            printRectangle(color, x, y);
        });
        group.add(up);
        lower.add(up);
        JRadioButton right = new JRadioButton("Вправо");
        right.addActionListener(e -> {
            x += 10;
            printRectangle(color, x, y);
        });
        group.add(right);
        lower.add(right);
        JRadioButton down = new JRadioButton("Вниз");
        down.addActionListener(e -> {
            y += 10;
            printRectangle(color, x, y);
        });
        group.add(down);
        lower.add(down);
    }

    private void printRectangle(Color color, int x, int y) {
        clearPanel();

        Graphics g = lower.getGraphics();
        g.setColor(color);
        g.drawRect(x, y, width, height);
    }

    private void clearPanel() {
        Graphics g = lower.getGraphics();
        Color c = new Color(238, 238, 238);
        g.setColor(c);
        g.fillRect(0, 30, lower.getWidth(), lower.getHeight());
    }

    public static Color getColor(String color) {
        Color res;
        switch (color) {
            case "Червоний":
                res = Color.RED;
                break;
            case "Зелений":
                res = Color.GREEN;
                break;
            case "Синій":
                res = Color.BLUE;
                break;
            default:
                res = Color.BLACK;
        }
        return res;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new MovingShapesApp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
