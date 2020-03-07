import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCanvas extends JPanel {

    long lastFrameTime;
    private final MainCircles controller;

    MainCanvas(MainCircles controller) {
        lastFrameTime = System.nanoTime();
        this.controller = controller;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (SwingUtilities.isLeftMouseButton(e)) {
                    controller.onLeftMouseBtnClicked(e);
                }
                if (SwingUtilities.isRightMouseButton(e)) {
                    controller.onRightMouseBtnClicked(e);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        controller.onCanvasRepainted(this, g, deltaTime);

        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lastFrameTime = currentTime;
        repaint();
    }

    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return 0; }
    public int getBottom() { return getHeight() - 1; }
}
