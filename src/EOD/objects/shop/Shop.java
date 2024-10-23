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
    private EchoesObjects shopBg, sidePanel;
    private EchoesObjects item1, item2, item3, item4;
    private EchoesObjects buyButton, closeButton;
    private double height, width;


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

    // public void hideShop() {
    //     // Hide all components
    //     if (shopBg != null) {
    //         // Remove all components from shopBg
    //         shopBg.removeAll();
    //         // Remove shopBg from the layered pane
    //         world.getLayeredPane().remove(shopBg);
    //         // Reset all object references
    //         shopBg = null;
    //         sidePanel = null;
    //         item1 = null;
    //         item2 = null;
    //         item3 = null;
    //         item4 = null;
    //         buyButton = null;
    //         closeButton = null;
    //     }
        
    //     // Refresh the display
    //     world.getLayeredPane().revalidate();
    //     world.getLayeredPane().repaint();
    //     world.revalidate();
    //     world.repaint();
    // }

    public void showSidePanelBg(double width, double height){
        sidePanel = new EchoesObjects("shop", (int)(width * 0.3799), (int)(height* 0.240), (int)(width * 0.58), (int)(height * 0.58), "sidePanel_", false, false, 5);
        sidePanel.setVisible(true);
        shopBg.add(sidePanel);
    }

    public void showShopItem1(double width, double height){
        item1 = new EchoesObjects("shop", (int)(width * 0.152), (int)(height* 0.2528), (int)(width * 0.1578), (int)(height * 0.3), "item1_", false, true, 2);
        item1.setVisible(true);
        item1.addMouseListener(new MouseClickListener(this));
        item1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                onHover(e);
            }
        });
        shopBg.add(item1);

        item2 = new EchoesObjects("shop", (int)(width * 0.152), (int)(height* 0.5), (int)(width * 0.1578), (int)(height * 0.3), "item2_", false, true, 2);
        item2.setVisible(true);
        item2.addMouseListener(new MouseClickListener(this));
        item2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                onHover(e);
            }
        });
        shopBg.add(item2);

        item3 = new EchoesObjects("shop", (int)(width * 0.294), (int)(height* 0.258), (int)(width * 0.1578), (int)(height * 0.3), "item3_", false, true, 2);
        item3.setVisible(true);
        item3.addMouseListener(new MouseClickListener(this));
        item3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                onHover(e);
            }
        });
        shopBg.add(item3);

        item4 = new EchoesObjects("shop", (int)(width * 0.294), (int)(height* 0.505), (int)(width * 0.1578), (int)(height * 0.3), "item4_", false, true, 2);
        item4.setVisible(true);
        item4.addMouseListener(new MouseClickListener(this));
        item4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                onHover(e);
            }
        });
        shopBg.add(item4);
    }

    public void showBuyButton(double width, double height){
        buyButton = new EchoesObjects("shop", 
        (int)(width * 0.618), (int)(height* 0.717), 
        (int)(width * 0.13), (int)(height * 0.15), 
        "buy_", false, true, 2);
        buyButton.addMouseListener(new MouseClickListener(this));
        shopBg.add(buyButton);
        shopBg.setComponentZOrder(buyButton, 0);
    }

    public void showCloseButton(double width, double height) {
        closeButton = new EchoesObjects("shop", 
            (int)(width * 0.82), 
            (int)(height * 0.189), 
            (int)(width * 0.06), 
            (int)(height * 0.1),
            "close_", false, true, 2); 
        closeButton.setVisible(true);
        closeButton.addMouseListener(new MouseClickListener(this));
        shopBg.add(closeButton);
        shopBg.revalidate();
        shopBg.repaint(); 
    }

    public void showElementsInShop(){
        showShopBg();
        width = shopBg.getWidth();
        height = shopBg.getHeight();
        showShopItem1(width, height);
        showSidePanelBg(width, height);
        showBuyButton(width, height);
        showCloseButton(width, height);
    }

    @Override
    public void onClick(MouseEvent e) {     
        Object source = e.getSource();
        if(source == this){
            showElementsInShop();
            return;
        }
        buyButton.setVisible(true);

        if(source == item1){
            item1.setCurrentFrame(2);  // Show second image on click
            item1.repaint();
            sidePanel.setCurrentFrame(1);
            sidePanel.repaint();
            
            // sidePanel.setCurrentFrame(1);
            // sidePanel.repaint();
        }else if(source == item2){
            item2.setCurrentFrame(2);  // Show second image on click
            sidePanel.setCurrentFrame(2);
            sidePanel.repaint();
            item2.repaint();
            // sidePanel.setCurrentFrame(2);
            // showBuyButton(width, height);
            sidePanel.repaint();
        }else if(source == item3){
            item3.setCurrentFrame(2);  // Show second image on click
            sidePanel.setCurrentFrame(3);
            sidePanel.repaint();
            item3.repaint();
            // sidePanel.setCurrentFrame(3);
            // showBuyButton(width, height);
            // sidePanel.repaint();
        }else if(source == item4){
            item4.setCurrentFrame(2);  // Show second image on click
            sidePanel.setCurrentFrame(4);
            sidePanel.repaint();
            item4.repaint();
            // sidePanel.setCurrentFrame(4);
            // showBuyButton(width, height);
            // sidePanel.repaint();
        }else if (source == closeButton){
          //  world.getLayeredPane().remove(shopBg);
            //hideShop();
            // world.getLayeredPane().revalidate();
            // world.getLayeredPane().repaint();
            // world.revalidate();
            // world.repaint();

            // if (shopBg != null) {
            //     // shopBg.setVisible(false);
            //     // world.getLayeredPane().remove(shopBg);
            //     // shopBg.removeAll();
                
            //     // // Null out all references
            //     // sidePanel = null;
            //     // item1 = null;
            //     // item2 = null;
            //     // item3 = null;
            //     // item4 = null;
            //     // buyButton = null;
            //     // closeButton = null;
            //     // shopBg = null;
                
            //     // // Request garbage collection
            //     // System.gc();
                
            //     if (shopBg != null) shopBg.setVisible(false);
            //     if (sidePanel != null) sidePanel.setVisible(false);
            //     if (item1 != null) item1.setVisible(false);
            //     if (item2 != null) item2.setVisible(false);
            //     if (item3 != null) item3.setVisible(false);
            //     if (item4 != null) item4.setVisible(false);
            //     if (buyButton != null) buyButton.setVisible(false);
            //     if (closeButton != null) closeButton.setVisible(false);
        
            //     // Refresh everything
            //     world.getLayeredPane().revalidate();
            //     world.getLayeredPane().repaint();
            //     //world.revalidate();
            //    // world.repaint();
            // }

             shopBg.setVisible(false);
            // sidePanel.setVisible(false);
            // item1.setVisible(false);
            // item2.setVisible(false);
            // item3.setVisible(false);
            // item4.setVisible(false);
            // buyButton.setVisible(false);
            // closeButton.setVisible(false);          

            world.getLayeredPane().revalidate();
            world.getLayeredPane().repaint();
        }
    }  

    //ifix pa nako ang close button kay ang sidePanel pa ang maclose - sm

    // -sm on hover show sidePanel
    public void onHover(MouseEvent e) {
        Object source = e.getSource();
        if(source == item1) {
            sidePanel.setCurrentFrame(1);
            sidePanel.repaint();
        } else if(source == item2) {
            sidePanel.setCurrentFrame(2);
            sidePanel.repaint();
        } else if(source == item3) {
            sidePanel.setCurrentFrame(3);
            sidePanel.repaint();
        } else if(source == item4) {
            sidePanel.setCurrentFrame(4);
            sidePanel.repaint();
        }
    }
}
