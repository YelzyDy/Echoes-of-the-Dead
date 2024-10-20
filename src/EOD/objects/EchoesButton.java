/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class EchoesButton extends JButton {

    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;
    
    public EchoesButton() {
        setColor(Color.WHITE);
        colorOver = new Color(179, 250, 160);
        colorClick = new Color(152, 184, 144);
        borderColor = new Color(30, 136, 56);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorder(null); 
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(colorOver);
                over = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(color);
                over = false;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(colorClick);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (over) {
                    setBackground(colorOver);
                } else {
                    setBackground(color);
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the button background and border
        g2d.setColor(borderColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2d.setColor(getBackground());
        g2d.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);

        // Draw the button text
        g2d.setColor(getForeground()); // Use the button's text color
        g2d.setFont(getFont()); // Use the button's font
        String text = getText(); // Get the text of the button
        // Center the text
        int textWidth = g2d.getFontMetrics().stringWidth(text);
        int textHeight = g2d.getFontMetrics().getHeight();
        g2d.drawString(text, (getWidth() - textWidth) / 2, (getHeight() + textHeight) / 2 - 4); // Adjust y for vertical centering
    }

    // Getters and Setters
    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        repaint(); // Repaint to apply the new radius
    }
}
