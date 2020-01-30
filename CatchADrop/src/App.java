import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) {
        gameWindow = new GameWindow();
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setLocation(200, 100);
        gameWindow.setSize(906, 478);
        gameWindow.setResizable(false);
        gameWindow.setVisible(true);
        GameField gf = new GameField(gameWindow);
    }

    static GameWindow gameWindow;
}

class GameWindow extends JFrame {
    public void onRepaint(Graphics g) {
        g.fillOval(10, 10,200, 100);
        //g.setColor(Color.red);
        g.drawLine(10, 10, 100, 300);
    }
}

class GameField extends JPanel {

    public GameField(@NotNull GameWindow gw) {
        this.gw = gw;
        gw.add(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gw.onRepaint(g);
    }

    private GameWindow gw;
}
