package echoes.of.the.dead;

import java.awt.*;
import java.awt.event.MouseEvent; 

public class Shop extends EchoesObjects implements MouseInteractable {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public Shop() {
        super("shop",
              (int)(screenSize.width * 0.78),
              (int)(screenSize.height * 0.037),
              (int)(screenSize.width * 0.22),
              (int)(screenSize.height * 0.32),
              "shop", false, true, 2);
        this.addMouseListener(new MouseClickListener(this));
    }

    @Override
    public void onClick(MouseEvent e) {     
        
    }  
}
