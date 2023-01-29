import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawingShapesApp extends JFrame implements ActionListener {
    private int APP_WIDTH = 400;
    private int APP_HEIGHT = 400;
    private JPanel controlPanel;
    private JPanel printPanel;
    private JMenuBar mb = new JMenuBar();
    private JMenu colorMenu;
    private JMenu drawMenu;
    private JMenuItem greenI, blueI, squareI, circleI, triangleI;
    boolean squareS = false, circleS = false, triangleS = false;
    Color color;
    Polygon triangle;
    Polygon square;
    Polygon oval;
    private int size = 50;

    public DrawingShapesApp() {
        init();
    }

    private void init() {
        setTitle("Drawing Shapes App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        setSize(APP_WIDTH, APP_HEIGHT);
        controlPanel = new JPanel();
        setContentPane(controlPanel);
        controlPanel.setLayout(null);

        initComponents();

        printPanel = new JPanel();
        printPanel.setBounds(0, 0, APP_WIDTH, APP_HEIGHT);
        add(printPanel);

        setVisible(true);
    }

    private void initComponents() {
        colorMenu = new JMenu("Колір");
        drawMenu = new JMenu("Вивід");
        greenI = new JMenuItem("Зелений");
        blueI = new JMenuItem("Синій");
        squareI = new JCheckBoxMenuItem("Квадрат");
        circleI = new JCheckBoxMenuItem("Коло");
        triangleI = new JCheckBoxMenuItem("Трикутник");

        greenI.addActionListener(this);
        blueI.addActionListener(this);
        squareI.addActionListener(this);
        circleI.addActionListener(this);
        triangleI.addActionListener(this);

        colorMenu.add(greenI);
        colorMenu.add(blueI);
        drawMenu.add(squareI);
        drawMenu.add(circleI);
        drawMenu.add(triangleI);

        mb.add(colorMenu);
        mb.add(drawMenu);
        setJMenuBar(mb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == greenI) {
            color = Color.GREEN;
            fillShape();
        }
        if (e.getSource() == blueI) {
            color = Color.BLUE;
            fillShape();
        }
        if (e.getSource() == squareI) {
            AbstractButton button = (AbstractButton) e.getSource();
            boolean selected = button.getModel().isSelected();
            if (!selected)
                clearShape(1);
            else
                drawShape(1);
        }
        if (e.getSource() == circleI) {
            AbstractButton button = (AbstractButton) e.getSource();
            boolean selected = button.getModel().isSelected();
            if (!selected)
                clearShape(2);
            else
                drawShape(2);
        }
        if (e.getSource() == triangleI) {
            AbstractButton button = (AbstractButton) e.getSource();
            boolean selected = button.getModel().isSelected();
            if (!selected)
                clearShape(3);
            else
                drawShape(3);
        }
    }

    private void fillShape() {
        Graphics g = printPanel.getGraphics();
        if (color != null)
            g.setColor(color);
        if (square != null) {
            g.fillRect(square.xpoints[0], square.ypoints[0], size * 2, size * 2);
        }
        if (oval != null) {
            g.fillOval(oval.xpoints[0], oval.ypoints[0], size * 2, size * 2);
        }
        if (triangle != null) {
            g.fillPolygon(triangle);
        }
    }

    public void drawShape(int shape) {
        switch (shape) {
            case 1: {
                squareS = true;
                drawSquare();
                break;
            }
            case 2: {
                circleS = true;
                drawCircle();
                break;
            }
            case 3: {
                triangleS = true;
                drawTriangle();
                break;
            }
        }
    }

    public void drawTriangle() {
        int centerX = printPanel.getWidth() / 2;
        int centerY = printPanel.getHeight() / 2 + 50;

        Graphics g = printPanel.getGraphics();
        g.setColor(Color.BLACK);

        triangle = new Polygon();
        triangle.addPoint(centerX + size, centerY + size);
        triangle.addPoint(centerX - size, centerY + size);
        triangle.addPoint(centerX, centerY - size);

        g.drawPolygon(triangle);
    }

    public void drawCircle() {
        int centerX = printPanel.getWidth() / 2 + 80;
        int centerY = printPanel.getHeight() / 2 - 80;

        Graphics g = printPanel.getGraphics();
        g.setColor(Color.BLACK);

        oval = new Polygon();
        oval.addPoint(centerX - size, centerY - size);

        g.drawOval(centerX - size, centerY - size, size * 2, size * 2);
    }

    public void drawSquare() {
        int centerX = printPanel.getWidth() / 2 - 100;
        int centerY = printPanel.getHeight() / 2 - 80;

        Graphics g = printPanel.getGraphics();
        g.setColor(Color.BLACK);

        square = new Polygon();
        square.addPoint(centerX - size, centerY - size);

        g.drawRect(centerX - size, centerY - size, size * 2, size * 2);
    }

    public void clearShape(int shape) {
        Graphics g = printPanel.getGraphics();
        Color c = new Color(238, 238, 238);
        g.setColor(c);
        g.fillRect(0, 0, printPanel.getWidth(), printPanel.getHeight());
        switch (shape) {
            case 1: {
                squareS = false;
                square = null;
                if (circleS) {
                    drawShape(2);
                    fillShape();
                }
                if (triangleS) {
                    drawShape(3);
                    fillShape();
                }
                break;
            }
            case 2: {
                circleS = false;
                oval = null;
                if (squareS) {
                    drawShape(1);
                    fillShape();
                }
                if (triangleS) {
                    drawShape(3);
                    fillShape();
                }
                break;
            }
            case 3: {
                triangleS = false;
                triangle = null;
                if (circleS) {
                    drawShape(2);
                    fillShape();
                }
                if (squareS) {
                    drawShape(1);
                    fillShape();
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                new DrawingShapesApp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
