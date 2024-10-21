package EOD.objects;

import java.awt.*;
import java.awt.event.MouseEvent;

import EOD.listeners.MouseClickListener;
import EOD.MouseInteractable; 
import EOD.worlds.*;
public class Shop extends EchoesObjects implements MouseInteractable {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private World world;
    private EchoesObjects shopBg;
    private EchoesObjects item1;
    private EchoesObjects sidePanel;
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

    public void showShopBg(){
        shopBg = new EchoesObjects("shop", (int)(screenSize.width * 0.1), (int)(screenSize.height* 0.1), (int)(screenSize.width * 0.8), (int)(screenSize.height * 0.8), "shopbg", false, false, 1);
        shopBg.setVisible(true);
        shopBg.setLayout(null);
        world.getLayeredPane().add(shopBg, Integer.valueOf(2));
        world.getLayeredPane().setComponentZOrder(shopBg, 0); // Make sure it's on top
        world.getLayeredPane().revalidate();
        world.getLayeredPane().repaint();
    }

    public void showSidePanel(double width, double height){
        sidePanel = new EchoesObjects("shop", (int)(width * 0.2), (int)(height* 0.7), (int)(width * 0.5), (int)(height * 0.5), "sidePanel", false, false, 1);
        sidePanel.setVisible(true);
        shopBg.add(sidePanel);
    }

    public void showShopItem1(double width, double height){
        item1 = new EchoesObjects("shop", (int)(width * 0.1), (int)(height* 0.1), (int)(width * 0.3), (int)(height * 0.3), "item1_", false, true, 2);
        item1.setVisible(true);
        shopBg.add(item1);
    }

    @Override
    public void onClick(MouseEvent e) {     
        // sample for showing the shop
        showShopBg();
        double width = shopBg.getWidth(), height = shopBg.getHeight();;
        showShopItem1(width, height);
        showSidePanel(width, height);
    }  
}
