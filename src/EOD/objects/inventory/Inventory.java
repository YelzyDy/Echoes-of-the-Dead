package EOD.objects.inventory;

import EOD.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;
import java.awt.*;
import EOD.worlds.World;
import javax.swing.*;
import java.awt.event.MouseEvent;

public class Inventory extends EchoesObjects implements MouseInteractable{
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private EchoesObjects item1Icon, item2Icon, item3Icon, item4Icon, sideIcon;
    private double width, height;
    private double incrementingX;
    private double incrementValue;
    private JLabel item1Label, item2Label, item3Label, item4Label;
    private int item1Quantity = 0, item2Quantity = 0, item3Quantity = 0, item4Quantity = 0;

    public Inventory(World world) {
        super("inventory",
                (int) (screenSize.width * 0.09),
                (int) (screenSize.height * 0.3),
                (int) (screenSize.width * 0.25),
                (int) (screenSize.height * 0.11),
                "slots",
                false,
                false,
                1);
        world.getLayeredPane().add(this, Integer.valueOf(1));

        // Set width and height after calling the super constructor
        width = getWidth();
        height = getHeight();

        // Now initialize incrementValue after width is defined
        incrementValue = width * 0.16;
        incrementingX = width * 0.35;

        this.setLayout(null);
        initializeItems();
        initializeLabels();
    }

    public void initializeItems() {
        // Initial x-position for the first item
        incrementingX = width * 0.35;
    
        item1Icon = createInventoryItem(incrementingX, "item1_");
        incrementingX += incrementValue; // Move to the next column
    
        item2Icon = createInventoryItem(incrementingX, "item2_");
        incrementingX += incrementValue;
    
        item3Icon = createInventoryItem(incrementingX, "item3_");
        incrementingX += incrementValue;
    
        item4Icon = createInventoryItem(incrementingX, "item4_");
        sideIcon = new EchoesObjects("inventory", (int) (width * 0.09),
        (int) (height * 0.28),
        (int) (width * 0.085),
        (int) (height * 0.45), "items_",false, true, 4);
        add(sideIcon);
    }
    
    // Helper method to create an item icon
    private EchoesObjects createInventoryItem(double posX, String itemName) {
        EchoesObjects itemIcon = new EchoesObjects("inventory",
                (int) posX,
                (int) (height * 0.28),
                (int) (width * 0.085),
                (int) (height * 0.45),
                itemName,
                false,
                false,
                1);
        add(itemIcon);
        itemIcon.setVisible(false);
        itemIcon.addMouseListener(new MouseClickListener(this));
        return itemIcon;
    }
    
    private void initializeLabels() {
        // Initial x-position for the label, adjusted for spacing
        double labelXOffset = width * 0.43;
        int labelYPosition = (int)(height * 0.28);
        int labelWidth = (int)(width * 0.08);
        int labelHeight = (int)(height * 0.15);
    
        // Initialize each label with default quantity text and position
        item1Label = createItemLabel((int) (item1Icon.getPosX() + labelXOffset), labelYPosition, labelWidth, labelHeight, item1Quantity);
        item2Label = createItemLabel((int) (item2Icon.getPosX() + incrementValue + labelXOffset), labelYPosition, labelWidth, labelHeight, item2Quantity);
        item3Label = createItemLabel((int) (item3Icon.getPosX() + 2 * incrementValue + labelXOffset), labelYPosition, labelWidth, labelHeight, item3Quantity);
        item4Label = createItemLabel((int) (item4Icon.getPosX() + 3 * incrementValue + labelXOffset), labelYPosition, labelWidth, labelHeight, item4Quantity);
    }
    
    private JLabel createItemLabel(int x, int y, int width, int height, int quantity) {
        JLabel label = new JLabel("x" + quantity);
        label.setBounds(x, y, width, height);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        label.setVisible(true);
        label.setVisible(false);
        add(label);
        setComponentZOrder(label, 1);
        return label;
    }
    
    public void addItem1() {
        sideIcon.setVisible(true);
        sideIcon.setCurrentFrame(0);
        item1Quantity++;
        item1Icon.setVisible(true);
        item1Label.setVisible(true);
        item1Label.setText("x" + item1Quantity);
        item1Label.repaint();
    }
    
    public void addItem2() {
        sideIcon.setVisible(true);
        sideIcon.setCurrentFrame(1);
        item2Quantity++;
        item2Icon.setVisible(true);
        item2Label.setVisible(true);
        item2Label.setText("x" + item2Quantity);
        item2Label.repaint();
    }
    
    public void addItem3() {
        sideIcon.setVisible(true);
        sideIcon.setCurrentFrame(2);
        item3Quantity++;
        item3Icon.setVisible(true);
        item3Label.setVisible(true);
        item3Label.setText("x" + item3Quantity);
        item3Label.repaint();
    }
    
    public void addItem4() {
        sideIcon.setVisible(true);
        sideIcon.setCurrentFrame(3);
        item4Quantity++;
        item4Icon.setVisible(true);
        item4Label.setVisible(true);
        item4Label.setText("x" + item4Quantity);
        item4Label.repaint();
    }

    @Override
    public void onHover(MouseEvent e){
        Object source = e.getSource();
        if(source == item1Icon){
            sideIcon.setCurrentFrame(0);
        }else if(source == item2Icon){
            sideIcon.setCurrentFrame(1);
        }else if(source == item3Icon){
            sideIcon.setCurrentFrame(2);
        }if(source == item4Icon){
            sideIcon.setCurrentFrame(3);
        }
    }
}
