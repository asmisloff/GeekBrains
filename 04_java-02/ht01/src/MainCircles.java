import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int SPRITES_CAPACITY = 1000;
    private Background background;
    private static int numberOfActiveSprites = 10;

    private Sprite[] sprites = new Sprite[SPRITES_CAPACITY];

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");
        initApplication();

        MainCanvas canvas = new MainCanvas(this);
        add(canvas);
        setVisible(true);
    }

    private void initApplication() {
        for (int i = 0; i < numberOfActiveSprites; i++) {
            sprites[i] = new Ball();
        }
        background = new Background(1f, 1f,1f,0.5f,1f,0.5f);
    }

    public void onCanvasRepainted(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        for (int i = 0; i < numberOfActiveSprites; i++) {
            sprites[i].update(canvas, deltaTime);
        }

        background.update(deltaTime);
        canvas.setBackground(background.getColor());
    }

    private void render(MainCanvas canvas, Graphics g) {
        for (int i = 0; i < numberOfActiveSprites; i++) {
            sprites[i].render(canvas, g);
        }
    }

    public void onLeftMouseBtnClicked(MouseEvent e) {
        if (numberOfActiveSprites < SPRITES_CAPACITY) {
            int last = numberOfActiveSprites++;
            if (sprites[last] == null) {
                sprites[last] = new Ball();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Невозможно добавить больше шариков.");
        }
    }

    public void onRightMouseBtnClicked(MouseEvent e) {
        if (numberOfActiveSprites > 0) {
//            sprites[--numberOfSprites] = null;
            --numberOfActiveSprites;
        }
    }
}