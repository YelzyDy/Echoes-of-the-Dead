/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoes.of.the.dead;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;

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
    
    public void scaleImageList(int scaleFactor) {
        for (int i = 0; i < size; i++) {
            Image originalImage = (Image)imageList.get(i).get(0);
            Image scaledImage = originalImage.getScaledInstance(
                    originalImage.getWidth(null) * scaleFactor,
                    originalImage.getHeight(null) * scaleFactor,
                    Image.SCALE_SMOOTH
            );
            imageList.get(i).set(0, scaledImage);         
        }
    }
    
    public void resizeImageList(int width, int height){
        for (int i = 0; i < size; i++) {
            Image originalImage = (Image)imageList.get(i).get(0);
            Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
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

    
    public void clear() {
        imageList.clear();  // Clears the entire list of images
        size = 0;           // Resets the size counter
    }
}
