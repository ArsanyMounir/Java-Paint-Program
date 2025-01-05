package paintproject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.event.MouseListener;
import javax.swing.JColorChooser;
import java.util.List;
import java.awt.Point;
import java.awt.BasicStroke;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.Image;



/**
 *
 * @author Arsany
 */
public class PaintContent {

    JPanel buttonPanel;
    JPanel drawingArea;
    ArrayList<Shape> shapes;
    Color currentColor = Color.BLACK; 
    boolean isFilled = false;
    boolean isDashed = false;
    
    public PaintContent()
    {
        shapes = new ArrayList<>();
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 2, 10, 10)); 
        buttonPanel.setPreferredSize(new Dimension(200,0));
        
        addButton("Oval","Icons/oval.png",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetListeners();
                new OvalDrawer(drawingArea,shapes,currentColor,isFilled,isDashed);
            }
        });
        
        addButton("Rectangle","Icons/rectangle.png",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetListeners();
                new RectangleDrawer(drawingArea, shapes,currentColor,isFilled,isDashed);
            }
        });
        addButton("Line","Icons/line.png",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetListeners();
                new LineDrawer(drawingArea, shapes,currentColor,isDashed);
            }
        });
        addButton("Freehand","Icons/freehand.png",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetListeners();
                FreehandDrawer freehandDrawer = new FreehandDrawer(drawingArea, shapes, currentColor,isDashed);
            }
        });
        addButton("Eraser","Icons/eraser.png",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetListeners();
                EraserDrawer eraserDrawer = new EraserDrawer(drawingArea, shapes);
            }
        });
        addButton("Dashed Line","Icons/dots.png",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetListeners();
                isDashed = !isDashed;
            }
        });
        addButton("Filled?","Icons/Filled.png",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetListeners();
                isFilled = !isFilled;
            }
        
        });
        addButton("Color Picker","Icons/palette.png",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetListeners();
                Color selectedColor = JColorChooser.showDialog(null, "Choose Color", currentColor);
                if (selectedColor != null) {
                    currentColor = selectedColor;
                }            
            }
        });
        
        addButton("Save","Icons/disk.png",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetListeners();
                saveDrawing();
               
            }
        });
        addButton("Undo","Icons/undo.png",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetListeners();
                if(!shapes.isEmpty())
                {
                    if(shapes.get(shapes.size()-1).getType() == "Freehand" || shapes.get(shapes.size()-1).getType() == "Eraser")
                    {
                        while(shapes.get(shapes.size()-1).getType() == "Freehand" || shapes.get(shapes.size()-1).getType() == "Eraser")
                        {
                            shapes.remove(shapes.size()-1);
                        }
                    }
                    else
                    {
                        shapes.remove(shapes.size()-1);
                    }
                }
                    
                drawingArea.repaint();

            }
        });

        addButton("Delete All","Icons/trash.png",new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                resetListeners();
                shapes.clear();
                drawingArea.repaint();
            }
        });
       

        drawingArea = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setStroke(new BasicStroke(5.0F));

            for (Shape shape : shapes) {
                g2d.setColor(shape.getColor());
                if(shape.isDashed())
                    {
                        float[] dashPattern = {10,10};
                        g2d.setStroke(new BasicStroke(5.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,dashPattern,0));
                    }
                    else
                    {
                        g2d.setStroke(new BasicStroke(5.0f));
                    }
                if (shape.getType().equals("Oval")) {
                    if (shape.isFilled()) {
                        g2d.fillOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
                    } else {
                        g2d.drawOval(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
                    }
                } else if (shape.getType().equals("Rectangle")) {
                    if (shape.isFilled()) {
                        g2d.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
                    } else {
                        g2d.drawRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
                    }   
                }else if (shape.getType().equals("Line")) {
                        g2d.drawLine(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
                } else if (shape.getType().equals("Freehand")) {
                    List<Point> points = shape.getPoints();
                    for (int i = 0; i < points.size() - 1; i++) {
                        Point p1 = points.get(i);
                        Point p2 = points.get(i + 1);
                        g2d.drawLine(p1.x, p1.y, p2.x, p2.y); 
                    }
                }else if (shape.getType().equals("Eraser")) {
                    List<Point> points = shape.getPoints();
                    for (int i = 0; i < points.size() - 1; i++) {
                        Point p1 = points.get(i);
                        Point p2 = points.get(i + 1);
                        g2d.drawLine(p1.x, p1.y, p2.x, p2.y); 
                    }
                }
            }
        }
    };
        
        drawingArea.setBackground(Color.WHITE);

        
    }

   
    private void addButton(String label,String imagePath, ActionListener actionListener) {
        JButton button = new JButton();

        ImageIcon icon = new ImageIcon(imagePath);
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); 
        button.setIcon(new ImageIcon(scaledImage));

        button.setToolTipText(label);

        button.addActionListener(actionListener);
        buttonPanel.add(button);
    }

    private void resetListeners() {
        for (MouseListener listener : drawingArea.getMouseListeners()) {
            drawingArea.removeMouseListener(listener);
        }
    }
    
    
    private void saveDrawing() {
        BufferedImage image = new BufferedImage(drawingArea.getWidth(), drawingArea.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        drawingArea.paint(g2d); 
        g2d.dispose();

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Drawing");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PNG Images", "png"));
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".png")) {
                fileToSave = new File(fileToSave + ".png");
            }

            try {
                ImageIO.write(image, "png", fileToSave);
                JOptionPane.showMessageDialog(null, "Drawing saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving the drawing: " + ex.getMessage());
            }
        }
    }
}
    
   

