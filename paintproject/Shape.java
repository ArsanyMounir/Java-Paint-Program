
package paintproject;

/**
 *
 * @author Arsany
 */
import java.awt.*;
import java.util.List;

public class Shape {
    private String type;
    private Color color;
    private boolean isFilled;
    private boolean isDashed;
    private int x, y, width, height;
    private List<Point> points;
    

    public Shape(String type, Color color, boolean isFilled, int x, int y, int width, int height,boolean isDashed) {
        this.type = type;
        this.color = color;
        this.isFilled = isFilled;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isDashed=isDashed;
    }
    public Shape(String type, Color color, boolean filled, List<Point> points,boolean isDashed) {
        this.type = type;
        this.color = color;
        this.isFilled = filled;
        this.points = points;
        this.isDashed=isDashed;

    }

    public String getType() {
        return type;
    }

    public Color getColor() {
        return color;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public List<Point> getPoints() {
        return points;
    }
    public boolean isDashed()
    {
        return isDashed;
    }
}

