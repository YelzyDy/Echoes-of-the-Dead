package EOD.objects.shop;

import EOD.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.worlds.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Shop extends EchoesObjects implements MouseInteractable {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private World world;
    //objects
    private EchoesObjects shopBg;
    private EchoesObjects item1;
    private EchoesObjects item2;
    private EchoesObjects item3;
    private EchoesObjects item4;
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

    public void showSidePanelBg(double width, double height){
        sidePanel = new EchoesObjects("shop", (int)(width * 0.3799), (int)(height* 0.240), (int)(width * 0.58), (int)(height * 0.58), "sidePanel_", false, false, 5);
        sidePanel.setVisible(true);
        shopBg.add(sidePanel);
    }

    public void showShopItem1(double width, double height){
        item1 = new EchoesObjects("shop", (int)(width * 0.152), (int)(height* 0.2528), (int)(width * 0.1578), (int)(height * 0.3), "item1_", false, true, 2);
        item1.setVisible(true);
        item1.addMouseListener(new MouseClickListener(this));
        shopBg.add(item1);

        item2 = new EchoesObjects("shop", (int)(width * 0.152), (int)(height* 0.5), (int)(width * 0.1578), (int)(height * 0.3), "item2_", false, true, 2);
        item2.setVisible(true);
        item2.addMouseListener(new MouseClickListener(this));
        shopBg.add(item2);

        item3 = new EchoesObjects("shop", (int)(width * 0.294), (int)(height* 0.258), (int)(width * 0.1578), (int)(height * 0.3), "item3_", false, true, 2);
        item3.setVisible(true);
        item3.addMouseListener(new MouseClickListener(this));
        shopBg.add(item3);

        item4 = new EchoesObjects("shop", (int)(width * 0.294), (int)(height* 0.505), (int)(width * 0.1578), (int)(height * 0.3), "item4_", false, true, 2);
        item4.setVisible(true);
        item4.addMouseListener(new MouseClickListener(this));
        shopBg.add(item4);
    }

    public void showElementsInShop(){
        showShopBg();
        double width = shopBg.getWidth(), height = shopBg.getHeight();
        showShopItem1(width, height);
        showSidePanelBg(width, height);
    }

    @Override
    public void onClick(MouseEvent e) {     
        Object source = e.getSource();
        System.out.println("click");
        if(source == item1){
            sidePanel.setCurrentFrame(1);
            sidePanel.repaint();
            System.out.println("item1");
        }else if(source == item2){
            sidePanel.setCurrentFrame(2);
            sidePanel.repaint();
        }else if(source == item3){
            sidePanel.setCurrentFrame(3);
            sidePanel.repaint();
        }else if(source == item4){
            sidePanel.setCurrentFrame(4);
            sidePanel.repaint();
        }else if(source == this){
            showElementsInShop();
        }
    }  
}
