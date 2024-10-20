package EOD.objects;

import java.awt.*;
import java.awt.event.MouseEvent;

import EOD.listeners.MouseClickListener;
import EOD.MouseInteractable; 
import EOD.worlds.*;
public class Shop extends EchoesObjects implements MouseInteractable {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private World world;
    public Shop(World world) {
        super("shop",
              (int)(screenSize.width * 0.78),
              (int)(screenSize.height * 0.037),
              (int)(screenSize.width * 0.22),
              (int)(screenSize.height * 0.32),
              "shop", false, true, 2);
        this.addMouseListener(new MouseClickListener(this));
        this.world = world;
    }   

    @Override
    public void onClick(MouseEvent e) {     
        // sample for showing the shop
        EchoesObjects obj = new EchoesObjects("shop", (int)(screenSize.width * 0.1), (int)(screenSize.height* 0.1), (int)(screenSize.width * 0.8), (int)(screenSize.height * 0.8), "shopbg", false, false, 1);
        obj.setVisible(true);
        world.getLayeredPane().add(obj, Integer.valueOf(2));
        world.getLayeredPane().revalidate();
        world.getLayeredPane().repaint();
        world.getLayeredPane().setComponentZOrder(obj, 0); // Make sure it's on top

    }  
}
