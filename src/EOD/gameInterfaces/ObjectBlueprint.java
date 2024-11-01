package EOD.gameInterfaces;
import java.awt.Image;

public interface ObjectBlueprint extends Entity{

    public void setAllowHover(boolean allowHover);
    
    public void setIndex(int index);

    public int getIndex();

    public boolean isAnimated();

    public void setBounds(int posX, int posY);

    public void initializeSprites(String assetPackage, double width, double height);

    public void scaleSprites(double scale);

    public void scaleDownSprites(double scale);
   
    public void setCurrentFrame(int value);

    public String getAssetPackage();
    
    public void restartAnimation();
    
    public void updateAnimation();

    public int getCurrentFrame();

    public int getNumOfSprites();

    public Image getCurrentSprite();

    public void setWidth(int width);
    
    public void setHeight(int height);
    
    public void setDimensions(int width, int height);
}
