
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
public class FreehandDrawer {

    private JPanel drawingArea;
    private List<Shape> shapes;
    private Color currentColor;
    private List<Point> points;
    private boolean isDashed;
    public FreehandDrawer(JPanel drawingArea, List<Shape> shapes, Color currentColor,boolean dashed) {
        this.drawingArea = drawingArea;
        this.shapes = shapes;
        this.currentColor = currentColor;
        this.points = null;
        isDashed = dashed;


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
                    shapes.add(new Shape("Freehand", currentColor, false, points,isDashed));
                    drawingArea.repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        });

    }

}
