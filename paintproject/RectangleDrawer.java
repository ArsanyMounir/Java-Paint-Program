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
public class RectangleDrawer {
    private JPanel drawingArea;
    private List<Shape> shapes;
    private int startX,startY;
    private Color color;
    private boolean isDashed;

    public RectangleDrawer(JPanel drawingArea, List<Shape> shapes,Color currentColor,boolean filled,boolean dashed) {
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
                int width = Math.abs(endX-startX);  
                int height = Math.abs(endY-startY); 
                int x = Math.min(startX,endX);
                int y = Math.min(startY,endY);
                boolean isFilled = filled;  
            
                
                shapes.add(new Shape("Rectangle", color, isFilled,x , y, width, height,isDashed));

                drawingArea.repaint();
            }
        });
    }
    
}