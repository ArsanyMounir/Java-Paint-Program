
package paintproject;
import javax.swing.JFrame;
import java.awt.BorderLayout;

/**
 *
 * @author Arsany
 */
public class PaintProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("Paint");
        PaintContent content = new PaintContent();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setLayout(new BorderLayout());
        frame.add(content.buttonPanel, BorderLayout.WEST);
        
        frame.add(content.drawingArea,BorderLayout.CENTER);

        frame.setVisible(true);
    }
    
}
