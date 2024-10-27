package EOD.objects.inventory;

import EOD.MouseInteractable;
import EOD.characters.ProtagonistAttributes;
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
    private ProtagonistAttributes attributes;
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
        attributes = world.getProtag().getAttributes();
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
        sideIcon.setAllowHover(false);
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

    private String applyItemEffect(String itemName) {
        String effectMessage = null;
    
        switch (itemName) {
            case "Item 1":
                if (item1Quantity > 0) {
                    attributes.setHp(attributes.getHp() + 20); // Increase health
                    item1Quantity--;
                    updateLabel(item1Label, item1Quantity);
                    if (item1Quantity == 0) {
                        item1Icon.setVisible(false); // Hide the item icon if quantity is 0
                    }
                    effectMessage = "Health increased by 20";
                }
                break;
            case "Item 2":
                if (item2Quantity > 0) {
                    attributes.setAttack(attributes.getAttack() + 20); // Increase attack
                    item2Quantity--;
                    updateLabel(item2Label, item2Quantity);
                    if (item2Quantity == 0) {
                        item2Icon.setVisible(false); // Hide the item icon if quantity is 0
                    }
                    effectMessage = "Attack increased by 20";
                }
                break;
            case "Item 3":
                if (item3Quantity > 0) {
                    attributes.setMana(attributes.getMana() + 20); // Increase mana
                    item3Quantity--;
                    updateLabel(item3Label, item3Quantity);
                    if (item3Quantity == 0) {
                        item3Icon.setVisible(false); // Hide the item icon if quantity is 0
                    }
                    effectMessage = "Mana increased by 20";
                }
                break;
            case "Item 4":
                if (item4Quantity > 0) {
                    attributes.setHp(attributes.getHp() + 10); // Increase health
                    attributes.setAttack(attributes.getAttack() + 5); // Increase attack
                    attributes.setMana(attributes.getMana() + 10); // Increase mana
                    item4Quantity--;
                    updateLabel(item4Label, item4Quantity);
                    if (item4Quantity == 0) {
                        item4Icon.setVisible(false); // Hide the item icon if quantity is 0
                    }
                    effectMessage = "Health and Mana increased by 10, Attack increased by 5!";
                }
                break;
        }  

         // Check if all items are used up
        if (item1Quantity == 0 && item2Quantity == 0 && item3Quantity == 0 && item4Quantity == 0) {
            sideIcon.setVisible(false); // Hide sideIcon if all items are used up
        }
    
        return effectMessage;
    }
    
    
    // Helper method to update the label based on item quantity
    private void updateLabel(JLabel label, int quantity) {
        if (quantity > 0) {
            label.setText("x" + quantity);
            label.setVisible(true);
        } else {
            label.setVisible(false);
        }
    }
    
    @Override 
    public void onClick(MouseEvent e) {
        Object source = e.getSource();
        String itemName = "";
    
        if (source == item1Icon) {
            itemName = "Item 1";
        } else if (source == item2Icon) {
            itemName = "Item 2";
        } else if (source == item3Icon) {
            itemName = "Item 3";
        } else if (source == item4Icon) {
            itemName = "Item 4";
        }
    
        if (!itemName.isEmpty()) {
            int choice = JOptionPane.showOptionDialog(
                this,
                "Do you want to use " + itemName + "?",
                "Item Use Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Use", "Cancel"},
                "Use"
            );
    
            if (choice == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, applyItemEffect(itemName));
            } else {
                JOptionPane.showMessageDialog(this, itemName + " was not used.");
            }
        }
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
