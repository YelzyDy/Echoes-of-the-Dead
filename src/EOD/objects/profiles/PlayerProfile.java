package EOD.objects.profiles;

import EOD.gameInterfaces.Freeable;
import EOD.gameInterfaces.MouseInteractable;
import EOD.listeners.MouseClickListener;
import EOD.objects.EchoesObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PlayerProfile implements MouseInteractable, Freeable {
    private EchoesObjects profileBorder;
    private JLabel profilePicture;
    private final int PROFILE_SIZE;
    private final JLayeredPane parentPanel;
    private final JFileChooser fileChooser;
    private static final String[] SUPPORTED_FORMATS = {"jpg", "jpeg", "png", "gif"};

    public PlayerProfile(JLayeredPane panel) {
        this.parentPanel = panel;
        PROFILE_SIZE = (int)(panel.getWidth() * 0.06);
        
        // Initialize profile border
        profileBorder = new EchoesObjects(
            "profile",
            (int)(panel.getWidth() * 0.015),
            (int)(panel.getHeight() * 0.87),
            PROFILE_SIZE,
            PROFILE_SIZE,
            "player",
            false,
            true,
            2
        );
        profileBorder.setVisible(true);
        profileBorder.addMouseListener(new MouseClickListener(this));
        
        // Initialize profile picture label
        profilePicture = new JLabel();
        profilePicture.setBounds(
            profileBorder.getX(),
            profileBorder.getY(),
            PROFILE_SIZE,
            PROFILE_SIZE
        );
        profilePicture.setHorizontalAlignment(JLabel.CENTER);
        profilePicture.setVerticalAlignment(JLabel.CENTER);
        try{
            BufferedImage originalImage = ImageIO.read(getClass().getResource("/profile_assets/sampleProfile.jpg"));
            Image scaledImage = getScaledImage(originalImage, PROFILE_SIZE, PROFILE_SIZE);
            ImageIcon profileIcon = new ImageIcon(scaledImage);
            profilePicture.setIcon(profileIcon);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                parentPanel,
                "Error loading image: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
        // Initialize file chooser
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                String extension = getFileExtension(f);
                if (extension != null) {
                    for (String format : SUPPORTED_FORMATS) {
                        if (extension.equals(format)) return true;
                    }
                }
                return false;
            }

            @Override
            public String getDescription() {
                return "Image files (*.jpg, *.jpeg, *.png, *.gif)";
            }
        });
        
        // Add components to panel
        panel.add(profileBorder, Integer.valueOf(2));
        panel.add(profilePicture, Integer.valueOf(1));
    }

    @Override
    public void free(){
        profileBorder.free();
        profileBorder = null;
    }

    public void setVisible(boolean value){
            profileBorder.setVisible(value);
            profilePicture.setVisible(value);
    }

    public void setPanel(JLayeredPane panel) {
        panel.add(profileBorder, Integer.valueOf(2));
        panel.add(profilePicture, Integer.valueOf(1));
    }

    public void setProfilePicture() {
        int result = fileChooser.showOpenDialog(parentPanel);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                BufferedImage originalImage = ImageIO.read(selectedFile);
                if (originalImage != null) {
                    // Scale the image to fit the profile size while maintaining aspect ratio
                    Image scaledImage = getScaledImage(originalImage, PROFILE_SIZE, PROFILE_SIZE);
                    ImageIcon profileIcon = new ImageIcon(scaledImage);
                    profilePicture.setIcon(profileIcon);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                    parentPanel,
                    "Error loading image: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private Image getScaledImage(Image srcImg, int width, int height) {
        BufferedImage resizedImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        
        // Enable better quality rendering
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Calculate dimensions to maintain aspect ratio
        int srcWidth = srcImg.getWidth(null);
        int srcHeight = srcImg.getHeight(null);
        
        double ratio = Math.min(
            (double) width / srcWidth,
            (double) height / srcHeight
        );
        
        int scaledWidth = (int)(srcWidth * ratio);
        int scaledHeight = (int)(srcHeight * ratio);
        
        // Center the image
        int x = (width - scaledWidth) / 2;
        int y = (height - scaledHeight) / 2;
        
        g2.drawImage(srcImg, x, y, scaledWidth, scaledHeight, null);
        g2.dispose();
        
        return resizedImg;
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) {
            return null;
        }
        return name.substring(lastIndexOf + 1).toLowerCase();
    }

    @Override
    public void onClick(MouseEvent e) {
        setProfilePicture();
    }

    @Override
    public void onHover(MouseEvent e) {
        // Change cursor to indicate clickable area
        parentPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void onExit(MouseEvent e) {
        // Reset cursor
        parentPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}