package EOD.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * BufferedImageList class manages a list of BufferedImages and provides methods to manipulate them.
 */
public class ImageList {
    protected final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ArrayList<ArrayList<Object>> imageList = new ArrayList<>();
    private int size = 0;
    private int origHeight = 0;
    private int origWidth = 0;
   
    public void add(BufferedImage icon, double x){
        ArrayList<Object> imageDetails = new ArrayList<>();
        imageDetails.add(icon);
        imageDetails.add(x);
        origHeight = icon.getHeight();
        origWidth = icon.getWidth();
        imageList.add(imageDetails);
        size++;
    }
    
    public void add(BufferedImage icon){
        ArrayList<Object> imageDetails = new ArrayList<>();
        imageDetails.add(icon);
        origHeight = icon.getHeight();
        origWidth = icon.getWidth();
        imageList.add(imageDetails);
        size++;
    }
    
    public double getX(int pos){
        if (pos >= 0 && pos < size) {
            return (double) imageList.get(pos).get(1);
        }
        return 0.0;
    }

    public void removeLast(){
        if (size > 0) {
            imageList.remove(size - 1);
            size--;
        }
    }
    
    public BufferedImage get(int i){
        return (BufferedImage) imageList.get(i).get(0);
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void scaleImageList(double scaleFactor) {
        for (int i = 0; i < size; i++) {
            BufferedImage originalImage = (BufferedImage) imageList.get(i).get(0);
            int newWidth = (int) (originalImage.getWidth() * scaleFactor);
            int newHeight = (int) (originalImage.getHeight() * scaleFactor);
            BufferedImage scaledImage = resizeBufferedImage(originalImage, newWidth, newHeight);
            imageList.get(i).set(0, scaledImage);         
        }
    }
    
    public void scaleImageListDown(double scaleFactor) {
        if (scaleFactor <= 0 || scaleFactor >= 1) {
            System.out.println("Invalid scale factor. It must be between 0 and 1.");
            return;
        }
        scaleImageList(scaleFactor);
    }

    public void resetToOriginalSize() {
        for (int i = 0; i < size; i++) {
            BufferedImage originalImage = (BufferedImage) imageList.get(i).get(0);
            BufferedImage resizedImage = resizeBufferedImage(originalImage, origWidth, origHeight);
            imageList.get(i).set(0, resizedImage);
        }
    }
    
    public void resizeImageList(double width, double height){
        for (int i = 0; i < size; i++) {
            BufferedImage originalImage = (BufferedImage) imageList.get(i).get(0);
            BufferedImage resizedImage = resizeBufferedImage(originalImage, (int) width, (int) height);
            imageList.get(i).set(0, resizedImage);
        }
    }
    
    public void resizeImageAtIndex(int index, int width, int height) {
        if (index >= 0 && index < size) {
            BufferedImage originalImage = (BufferedImage) imageList.get(index).get(0);
            BufferedImage scaledImage = resizeBufferedImage(originalImage, width, height);
            imageList.get(index).set(0, scaledImage);
        }
    }
    
    public void replaceImageAtIndex(int index, BufferedImage newImage) {
        if (index >= 0 && index < size) {
            double currentX = (double) imageList.get(index).get(1);
            BufferedImage resizedImage = resizeBufferedImage(newImage, origWidth, origHeight);
            imageList.get(index).set(0, resizedImage);
            imageList.get(index).set(1, currentX);
        } else {
            System.out.println("Index out of bounds. Cannot replace image.");
        }
    }

    public void cropImageList() {
        for (int i = 0; i < size; i++) {
            BufferedImage originalImage = (BufferedImage) imageList.get(i).get(0);
            Rectangle boundingBox = findBoundingBox(originalImage);
            if (boundingBox != null) {
                BufferedImage croppedImage = originalImage.getSubimage(
                    boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height
                );
                imageList.get(i).set(0, croppedImage);
            } else {
                System.out.println("No pixels found to crop for image at index: " + i);
            }
        }
    }

    private Rectangle findBoundingBox(BufferedImage image) {
        int minX = image.getWidth();
        int minY = image.getHeight();
        int maxX = 0;
        int maxY = 0;
        boolean foundPixel = false;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if ((image.getRGB(x, y) >> 24) != 0x00) {
                    foundPixel = true;
                    if (x < minX) minX = x;
                    if (y < minY) minY = y;
                    if (x > maxX) maxX = x;
                    if (y > maxY) maxY = y;
                }
            }
        }
        return foundPixel ? new Rectangle(minX, minY, maxX - minX + 1, maxY - minY + 1) : null;
    }

    private BufferedImage resizeBufferedImage(BufferedImage originalImage, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(originalImage, 0, 0, width, height, null);
        g2d.dispose();
        return resizedImage;
    }

    public void clear() {
        imageList.clear();
        size = 0;
    }
}
