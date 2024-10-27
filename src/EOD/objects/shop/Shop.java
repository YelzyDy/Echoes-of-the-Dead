package EOD.objects.shop;

import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import EOD.objects.inventory.Inventory;
import EOD.worlds.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;

public class Shop extends EchoesObjects {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private World world;
    // objects
    private EchoesObjects sidePanel;
    private EchoesObjects item1, item2, item3, item4;
    private EchoesObjects buyButton, closeButton;
    private double height, width;
    private int itemToBuy;
    private int item1Stock, item2Stock, item3Stock, item4Stock;
    private int money;
    private JLabel moneyLabel;
    private Inventory inventory;

    public Shop(World world) {
        super("shop", (int) (screenSize.width * 0.1), (int) (screenSize.height * 0.1), 
              (int) (screenSize.width * 0.8), (int) (screenSize.height * 0.8), "shopbg", false, false, 1);
        this.world = world;
        // Initialize stocks
        item1Stock = item2Stock = item3Stock = item4Stock = 3;
        setVisible(false);
        setLayout(null);
        world.getLayeredPane().add(this, Integer.valueOf(2));
        world.getLayeredPane().setComponentZOrder(this, 0);
        money = world.getProtag().getAttributes().getMoney();
        showElementsInShop();
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    private void showSidePanelBg(double width, double height) {
        sidePanel = new EchoesObjects("shop", (int) (width * 0.3799), (int) (height * 0.240),
                                      (int) (width * 0.58), (int) (height * 0.58), "sidePanel_", false, false, 5);
        sidePanel.setVisible(true);
        add(sidePanel);
    }

    private void showShopItems(double width, double height) {
        item1 = createItem("item1_", width * 0.152, height * 0.2528, width * 0.1578, height * 0.3);
        item2 = createItem("item2_", width * 0.152, height * 0.5, width * 0.1578, height * 0.3);
        item3 = createItem("item3_", width * 0.294, height * 0.258, width * 0.1578, height * 0.3);
        item4 = createItem("item4_", width * 0.294, height * 0.505, width * 0.1578, height * 0.3);

        add(item1);
        add(item2);
        add(item3);
        add(item4);
    }

    public void makeElementsVisible(){
        setVisible(true);
        moneyLabel.setVisible(true);
        sidePanel.setVisible(true);
        item1.setVisible(true);
        item2.setVisible(true);
        item3.setVisible(true);
        item4.setVisible(true);

    }

    private void showMoney(double width, double height) {
        // Create a panel for better layout control
        JPanel moneyPanel = new JPanel();
        moneyPanel.setLayout(null);
        moneyPanel.setOpaque(false); // Make panel transparent
        moneyPanel.setBounds((int)(width * 0.68), (int)(height * 0.19), (int)(width * 0.1), (int)(height * 0.1)); // Position at top

        // Create money label with formatted text
        moneyLabel = new JLabel("" + money);
        moneyLabel.setFont(new Font("Arial", Font.BOLD, (int)(height * 0.03))); // Scaled font size
        moneyLabel.setForeground(new Color(238,218,180,255)); // Gold color
        moneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        
        // Set the bounds for the label within the panel
        moneyLabel.setBounds(0, 0, 
                            (int)(width * 0.1), (int)(height * 0.1));

        moneyPanel.add(moneyLabel);
        add(moneyPanel);
        setComponentZOrder(moneyPanel, 0);
    }

    private EchoesObjects createItem(String imageName, double x, double y, double width, double height) {
        EchoesObjects item = new EchoesObjects("shop", (int) x, (int) y, (int) width, (int) height, imageName, false, true, 3);
        item.addMouseListener(new MouseClickListener(this));
        item.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(MouseEvent e) {
                onHover(e);
            }
        });
        return item;
    }

    private void showBuyButton(double width, double height) {
        buyButton = new EchoesObjects("shop", (int) (width * 0.618), (int) (height * 0.717),
                                      (int) (width * 0.13), (int) (height * 0.15), "buy_", false, true, 2);
        buyButton.addMouseListener(new MouseClickListener(this));
        add(buyButton);
        setComponentZOrder(buyButton, 0);
    }

    private void showCloseButton(double width, double height) {
        closeButton = new EchoesObjects("shop", (int) (width * 0.82), (int) (height * 0.189),
                                        (int) (width * 0.056), (int) (height * 0.1), "close_", false, true, 2);
        closeButton.setVisible(true);
        closeButton.addMouseListener(new MouseClickListener(this));
        add(closeButton);
        setComponentZOrder(closeButton, 0);
    }

    public void showElementsInShop() {
        width = getWidth();
        height = getHeight();

        showShopItems(width, height);
        showSidePanelBg(width, height);
        showBuyButton(width, height);
        showCloseButton(width, height);
        showMoney(width, height);
    }

    @Override
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        buyButton.setVisible(true);

        if (source == item1) {
            handleItemClick(1, item1, item1Stock);
        } else if (source == item2) {
            handleItemClick(2, item2, item2Stock);
        } else if (source == item3) {
            handleItemClick(3, item3, item3Stock);
        } else if (source == item4) {
            handleItemClick(4, item4, item4Stock);
        } else if (source == buyButton) {
            buy();
        } else if (source == closeButton) {
            hideShop();
        }
    }

    private void handleItemClick(int itemNumber, EchoesObjects item, int stock) {
        itemToBuy = itemNumber;
        if (stock > 0) {
            sidePanel.setCurrentFrame(itemNumber);
            sidePanel.repaint();
        }
    }

    public void buy() {
        switch (itemToBuy) {
            case 1 -> {
                if (item1Stock != 0) {
                    updateStock(item1, --item1Stock);
                    updateMoney(1); // Deduct money for item 1
                }
            }
            case 2 -> {
                if (item2Stock != 0) {
                    updateStock(item2, --item2Stock);
                    updateMoney(2); // Deduct money for item 2
                }
            }
            case 3 -> {
                if (item3Stock != 0) {
                    updateStock(item3, --item3Stock);
                    updateMoney(3); // Deduct money for item 3
                }
            }
            case 4 -> {
                if (item4Stock != 0) {
                    updateStock(item4, --item4Stock);
                    updateMoney(4); // Deduct money for item 4
                }
            }
        }
        moneyLabel.setText(money + ""); // Update money display
    }    

    private void updateStock(EchoesObjects item, int stock) {
        if (stock == 0) {
            item.setCurrentFrame(2);  // Show "sold out" frame
            item.repaint();
            item.setAllowHover(false);
        }
    }

    private void updateMoney(int item){
        switch(item){
            case 1 -> {if(money >= 10) money -= 10; inventory.addItem1();}
            case 2 -> {if(money >= 10) money -= 10; inventory.addItem2();}
            case 3 -> {if(money >= 10) money -= 10; inventory.addItem3();}
            case 4 -> {if(money >= 10) money -= 10; inventory.addItem4();}
        }
    }

    @Override
    public void onHover(MouseEvent e) {
        Object source = e.getSource();
        if (source == item1 && item1Stock == 0) {
            updateUnavailableItem(item1);
        } else if (source == item2 && item2Stock == 0) {
            updateUnavailableItem(item2);
        } else if (source == item3 && item3Stock == 0) {
            updateUnavailableItem(item3);
        } else if (source == item4 && item4Stock == 0) {
            updateUnavailableItem(item4);
        }
    }

    private void updateUnavailableItem(EchoesObjects item) {
        item.setCurrentFrame(2);  // Show "unavailable" frame
        item.revalidate();
        item.repaint();
    }

    @Override
    public void onExit(MouseEvent e) {
        // Add logic if needed for onExit
    }

    private void hideShop() {
        if (isVisible()) {;
            setVisible(false);

        }
    }
}
