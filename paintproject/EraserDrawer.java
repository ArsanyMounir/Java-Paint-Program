
package paintproject;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.List;
import javax.swing.*;
import java.util.ArrayList;

/**
 *
 * @author Arsany
 */
public class EraserDrawer  {

    private JPanel drawingArea;
    private List<Shape> shapes;
    private List<Point> points;

    public EraserDrawer(JPanel drawingArea, List<Shape> shapes) {
        this.drawingArea = drawingArea;
        this.shapes = shapes;
        this.points = null;

        drawingArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points = new ArrayList<>();
                points.add(e.getPoint());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (points != null && points.size() > 1) {
                    points.add(e.getPoint());
                    points = null;
                    drawingArea.repaint();
                }

            }
        });

        drawingArea.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (points != null) {
                    points.add(e.getPoint());
                    shapes.add(new Shape("Eraser", Color.WHITE, false, points,false));
                    drawingArea.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

    }

}
