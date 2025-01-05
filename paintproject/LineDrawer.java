/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paintproject;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
/**
 *
 * @author Arsany
 */
public class LineDrawer {
    private JPanel drawingArea;
    private List<Shape> shapes;
    private int startX, startY;
    private Color color;
    private boolean isDashed;

    public LineDrawer(JPanel drawingArea, List<Shape> shapes,Color currentColor ,boolean dashed) {
        this.drawingArea = drawingArea;
        this.shapes = shapes;
        this.color = currentColor;
        isDashed = dashed;
        drawingArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                int endX = e.getX();
                int endY = e.getY();
                boolean isFilled = true; 

                shapes.add(new Shape("Line", color, isFilled, startX, startY, endX, endY,isDashed));

                drawingArea.repaint();
            }
        });
    }
}
