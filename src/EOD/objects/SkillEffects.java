package EOD.objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Timer;

import javax.swing.JLayeredPane;
import java.awt.Image;

import EOD.entities.Entity;
import EOD.scenes.SceneBuilder;

public class SkillEffects extends EchoesObjects {
    private boolean isLooping;
    private int stopFrame;
    private boolean isActive;
    private Entity target;
    private double offsetX;
    private double offsetY;
    private Timer gameLoop;
    private SceneBuilder panel;
    
    public SkillEffects(String assetPackage, int x, int y, int width, int height, 
                      String type, int numOfSprites, SceneBuilder panel) {
        super(assetPackage, x, y, width, height, type, true, false, numOfSprites);
        this.isLooping = true;
        this.stopFrame = numOfSprites - 1;
        this.isActive = false;
        this.panel = panel;
        setOpaque(false);
        setVisible(true);
    }

    public void updateEffect() {
        if (!isActive) return;
        panel.setComponentZOrder(this, 0);
        updateAnimation();
        
        if (target != null) {
            double newX = target.getPosX() + offsetX;
            double newY = target.getPosY() + offsetY;
            setPosX(newX);
            setPosY(newY);
            setBounds((int)newX, (int)newY, getWidth(), getHeight());
        }
        
        // Check if we've reached the stop frame
        if (!isLooping && getCurrentFrame() >= stopFrame) {
            stop();  // This will hide and remove the effect
            return;  // Exit early to prevent further updates
        }
        
        // Only repaint if the effect is still active
        if (isActive) {
            repaint();
        }
    }

    @Override
    public void updateAnimation() {
        if (!isActive) return;
        
        setCurrentFrame(getCurrentFrame() + 1);
        
        // If we're looping, reset the frame counter when we reach the end
        if (isLooping && getCurrentFrame() >= getNumOfSprites()) {
            setCurrentFrame(0);
        }
        // If we're not looping, ensure we don't exceed the number of sprites
        else if (!isLooping) {
            if (getCurrentFrame() >= getNumOfSprites()) {
                setCurrentFrame(getNumOfSprites() - 1);
            }
        }
    }


    public void setLooping(boolean isLooping) {
        this.isLooping = isLooping;
    }
    public void setStopFrame(int frame) {
        this.stopFrame = Math.min(frame, getNumOfSprites() - 1);
    }

    public void bindToTarget(Entity target, double offsetX, double offsetY) {
        this.target = target;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        updateEffect(); // Immediately update position
    }

    public void unbindTarget() {
        this.target = null;
    }

      
    public void play() {
        if (isActive) return;
        
        isActive = true;
        setVisible(true);
        
        if (!panel.isAncestorOf(this)) {
            // Ensure the panel uses null layout for absolute positioning
            panel.setLayout(null);
            
            // Add this component to the top layer of the panel
            panel.add(this, JLayeredPane.DRAG_LAYER);
        }
        
        // Set bounds for absolute positioning
        setBounds((int)getPosX(), (int)getPosY(), getWidth(), getHeight());
        
        restartAnimation();
        panel.revalidate();
        panel.repaint();
        
        // Debug info
        System.out.println("Playing effect: Active=" + isActive + 
                          " Visible=" + isVisible() + 
                          " Bounds=" + getBounds() + 
                          " Current frame=" + getCurrentFrame());
    }
    
    public void stop() {
        isActive = false;
        setVisible(false);
        setCurrentFrame(0);  // Reset the frame counter
        
        if (panel.isAncestorOf(this)) {
            panel.remove(this);
            panel.revalidate();
            panel.repaint();
        }
        
        // Clean up any bindings
        unbindTarget();
    }
    
    public void pause() {
        isActive = false;
    }
    
    public void resume() {
            isActive = true;
    }
    
    public boolean isActive() {
        return isActive;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!isActive || !isVisible()) return;
        
        // Don't call super.paintComponent() since we want full control over rendering
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        
        // Draw relative to component bounds
        Image currentSprite = getCurrentSprite();
        if (currentSprite != null) {
            g2d.drawImage(currentSprite, 0, 0, getWidth(), getHeight(), null);
        }
    }
    
    @Override
    public void draw(Graphics g) {
        if (!isActive || !isVisible()) return;
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, 
                            RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(getCurrentSprite(), (int)getPosX(), (int)getPosY(), null);
    }
    
    // Clean up resources when no longer needed
    public void dispose() {
        stop();
        if (gameLoop != null) {
            gameLoop.cancel();
            gameLoop.purge();
        }
    }
}