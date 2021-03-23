
package hotelView;

import java.awt.Dimension;
import javax.swing.JFrame;

import hotel.Hotel;

public class MainScreen {
 
    public MainScreen(int width, int height , String title,Hotel hotel)
    {
        //this.game=game;
        JFrame frame = new JFrame(title);
        frame.setPreferredSize(new Dimension (width, height));
        frame.setMaximumSize(new Dimension (width, height));
        frame.setMinimumSize(new Dimension (width, height));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(hotel);
        frame.setVisible(true);
        
    }
   
    
}
