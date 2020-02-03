import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        GameField gameField = new GameField();
        GameWindow w = new GameWindow(gameField);

        gameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                double dropLeft = gameField.dropLeft;
                double dropTop = gameField.dropTop;
                double dropRight = dropLeft + gameField.drop.getWidth(null);
                double dropBottom = dropTop + gameField.drop.getHeight(null);
                boolean isDrop = (dropLeft <= x&&x <= dropRight) && (dropTop <= y&&y <= dropBottom);
//                showMessageDialog(null,
//                        String.format("x = %d, y = %d; dropTop = %f, dropLeft = %f, dropRight = %f, dropBottom = %f",
//                                x, y, dropTop, dropLeft, dropRight, dropBottom));
                if (isDrop) {
                    gameField.dropTop = -100;
                    gameField.dropLeft = (int)(Math.random() * (gameField.getWidth() - gameField.drop.getWidth(null)));
                    gameField.velocity += 20;
                    w.incScore();
                }
            }
        });

        w.setVisible(true);
    }

    static class GameWindow extends JFrame {

        public GameWindow(GameField gameField) {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            setLocation(200, 100);
            setSize(906, 478);
            setResizable(false);
            add(gameField);
            setTitle("Score: " + score);
        }

        @Override
        public void paintComponents(Graphics g) {
            super.paintComponents(g);
            //onRepaint(g);
        }

        public void incScore() {
            setTitle("Score: " + ++score);
        }

        int score = 0;
    }

    static class GameField extends JPanel {

        public GameField() throws IOException {
            background = ImageIO.read(App.class.getResourceAsStream("background.png"));
            gameOver = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.png"));
            drop = ImageIO.read(App.class.getResourceAsStream("drop.png"));
            setDoubleBuffered(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(background, 1, 1,null);
            dropDown(dropLeft, velocity, g);
            if (dropTop > getParent().getHeight()) {
                g.drawImage(gameOver, 280, 120, null);
                repaint();
                return;
            }

            repaint(dropLeft-20, dropTop-20, drop.getWidth(null) + 40, drop.getHeight(null) + 40);
        }

        void dropDown(int x0, int velocity, Graphics g) {
            long t = System.nanoTime();
            double dt = (t - lastFrameTime) * 1e-9;
            lastFrameTime = t;
            g.drawImage(drop, x0, dropTop += (int)(velocity * dt), null);
        }

        Image background;
        Image gameOver;
        Image drop;
        int dropLeft = 100;
        int dropTop = -100;
        long lastFrameTime = System.nanoTime();
        int velocity = 200;
    }

}
