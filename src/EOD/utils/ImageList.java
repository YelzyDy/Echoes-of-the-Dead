/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EOD.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author Joana
 */
public class ImageList {
    protected final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    ArrayList<ArrayList<Object>> imageList = new ArrayList<>();
    private int size = 0;
    private int origHeight = 0;
    private int origWidth = 0;
   
    
    public void add(Image icon, double x){
        ArrayList<Object> imageDetails = new ArrayList<>();
            
        imageDetails.add(icon);
        imageDetails.add(x);
        origHeight = icon.getHeight(null);
        origWidth = icon.getWidth(null);
        imageList.add(imageDetails);
        size++;
    }
    
    public void add(Image icon){
        ArrayList<Object> imageDetails = new ArrayList<>();
        imageDetails.add(icon);
        origHeight = icon.getHeight(null);
        origWidth = icon.getWidth(null);
        imageList.add(imageDetails);
        size++;
    }
    
    public double getX(int pos){
        for(int i = 0; i < size; i++){
            if(i == pos){
                return (double) imageList.get(i).get(1);
            }
        }
        return 0.0;
    }
    public void removeLast(){
        imageList.remove(size - 1);
        size--;
    }
    
    public Image get(int i){
        return (Image) imageList.get(i).get(0);
    }
    
    public int getSize(){
        return this.size;
    }
    
    public void scaleImageList(double scaleFactor) {
        for (int i = 0; i < size; i++) {
            Image originalImage = (Image)imageList.get(i).get(0);
            int newWidth = (int)(originalImage.getWidth(null) * scaleFactor);
            int newHeight = (int)(originalImage.getHeight(null) * scaleFactor);
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            imageList.get(i).set(0, scaledImage);         
        }
    }
    
    public void scaleImageListDown(double scaleFactor) {
        if (scaleFactor <= 0 || scaleFactor >= 1) {
            System.out.println("Invalid scale factor. It must be between 0 and 1.");
            return;
        }
    
        for (int i = 0; i < size; i++) {
            Image originalImage = (Image) imageList.get(i).get(0);
            int newWidth = (int) (originalImage.getWidth(null) * scaleFactor);
            int newHeight = (int) (originalImage.getHeight(null) * scaleFactor);
            
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            imageList.get(i).set(0, scaledImage);
        }
    }

    
    public void resizeImageList(double width, double height){
        for (int i = 0; i < size; i++) {
            Image originalImage = (Image)imageList.get(i).get(0);
            Image scaledImage = originalImage.getScaledInstance((int)width, (int)height, Image.SCALE_SMOOTH);
            imageList.get(i).set(0, scaledImage);
        }
    }
    
    public void resizeImageAtIndex(int index, int width, int height) {
        if (index >= 0 && index < size) {
            Image originalImage = (Image) imageList.get(index).get(0);
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            imageList.get(index).set(0, scaledImage);
        }
    }
    
    public void replaceImageAtIndex(int index, Image newImage) {
        if (index >= 0 && index < size) {
            // Preserve the current x position
            double currentX = (double) imageList.get(index).get(1);

            // Get the original image details
            Image originalImage = (Image) imageList.get(index).get(0);
            int originalHeight = originalImage.getHeight(null); // Store the original height
            int originalWidth = originalImage.getWidth(null);   // Store the original width

            // Update the image with the new one
            imageList.get(index).set(0, newImage);

            // Resize the new image to match the dimensions of the original image
            Image resizedImage = newImage.getScaledInstance(originalWidth, originalHeight, Image.SCALE_SMOOTH);
            imageList.get(index).set(0, resizedImage);

            // Set back the x position
            imageList.get(index).set(1, currentX);
        } else {
            System.out.println("Index out of bounds. Cannot replace image.");
        }
    }

    public void cropAllImages() {
        for (int i = 0; i < size; i++) {
            Image originalImage = (Image) imageList.get(i).get(0);

            // Convert the image to a BufferedImage for pixel manipulation
            BufferedImage bufferedImage = new BufferedImage(
                originalImage.getWidth(null),
                originalImage.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
            );

            Graphics g = bufferedImage.getGraphics();
            g.drawImage(originalImage, 0, 0, null);
            g.dispose();

            // Find the bounding box of non-transparent pixels
            Rectangle boundingBox = findBoundingBox(bufferedImage);

            if (boundingBox != null) {
                // Crop the image using the bounding box
                BufferedImage croppedImage = bufferedImage.getSubimage(
                    boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height
                );

                // Update the image in the list with the cropped image
                imageList.get(i).set(0, croppedImage);
            } else {
                System.out.println("No pixels found to crop for image at index: " + i);
            }
        }
    }

    // Method to find the bounding box of non-transparent pixels
    private Rectangle findBoundingBox(BufferedImage image) {
        int minX = image.getWidth();
        int minY = image.getHeight();
        int maxX = 0;
        int maxY = 0;

        boolean foundPixel = false;

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                // Check if the pixel is not transparent
                if ((image.getRGB(x, y) >> 24) != 0x00) {
                    foundPixel = true;
                    if (x < minX) minX = x;
                    if (y < minY) minY = y;
                    if (x > maxX) maxX = x;
                    if (y > maxY) maxY = y;
                }
            }
        }

        if (foundPixel) {
            return new Rectangle(minX, minY, maxX - minX + 1, maxY - minY + 1);
        } else {
            return null; // No pixels found
        }
    }
    
    public void clear() {
        imageList.clear();  // Clears the entire list of images
        size = 0;           // Resets the size counter
    }

    public void removeWhiteBackground(int threshold) {
        for (int i = 0; i < size; i++) {
            Image originalImage = (Image) imageList.get(i).get(0);
            
            // Convert to BufferedImage
            BufferedImage bufferedImage = new BufferedImage(
                originalImage.getWidth(null),
                originalImage.getHeight(null),
                BufferedImage.TYPE_INT_ARGB
            );
    
            Graphics g = bufferedImage.getGraphics();
            g.drawImage(originalImage, 0, 0, null);
            g.dispose();
    
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            
            // Create a boolean array to mark pixels to be made transparent
            boolean[][] toRemove = new boolean[height][width];
            
            // Start flood fill from the edges
            Stack<Point> stack = new Stack<>();
            
            // Add edge pixels that are white to the stack
            for (int x = 0; x < width; x++) {
                checkAndAddPixel(bufferedImage, x, 0, threshold, stack, toRemove);
                checkAndAddPixel(bufferedImage, x, height - 1, threshold, stack, toRemove);
            }
            for (int y = 0; y < height; y++) {
                checkAndAddPixel(bufferedImage, 0, y, threshold, stack, toRemove);
                checkAndAddPixel(bufferedImage, width - 1, y, threshold, stack, toRemove);
            }
            
            // Flood fill from edge white pixels
            while (!stack.isEmpty()) {
                Point p = stack.pop();
                
                // Check all 4 neighboring pixels
                checkAndAddPixel(bufferedImage, p.x + 1, p.y, threshold, stack, toRemove);
                checkAndAddPixel(bufferedImage, p.x - 1, p.y, threshold, stack, toRemove);
                checkAndAddPixel(bufferedImage, p.x, p.y + 1, threshold, stack, toRemove);
                checkAndAddPixel(bufferedImage, p.x, p.y - 1, threshold, stack, toRemove);
            }
            
            // Create the final image with transparent background
            BufferedImage modifiedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (toRemove[y][x]) {
                        modifiedImage.setRGB(x, y, 0x00000000); // Transparent
                    } else {
                        modifiedImage.setRGB(x, y, bufferedImage.getRGB(x, y)); // Keep original
                    }
                }
            }
            
            imageList.get(i).set(0, modifiedImage);
        }
    }
    
    private void checkAndAddPixel(BufferedImage image, int x, int y, int threshold, 
                                Stack<Point> stack, boolean[][] toRemove) {
        // Check bounds
        if (x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()) {
            return;
        }
        
        // If already processed, skip
        if (toRemove[y][x]) {
            return;
        }
        
        // Get pixel color
        int rgb = image.getRGB(x, y);
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        
        // Check if pixel is white enough
        if (red > threshold && green > threshold && blue > threshold) {
            toRemove[y][x] = true;
            stack.push(new Point(x, y));
        }
    }
}
