package src;

import java.awt.image.BufferedImage;

public class SpecialImage{
    private BufferedImage img;
    private int ID;
    private String name;

    /**
     * Getter for getting BufferedImage associated to this object
     */

    public BufferedImage getImg() {
        return img;
    }


    /**
     * Getter for getting name of file associated to this object
     */
    public String getName() {return name;}


    /**
     * Setter for setting id of this object. Must be unique.
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Getter for getting ID associated to this object. Is always unique.
     */
    public int getID() {
        return ID;
    }

    /**
     * This is the constructor for SpecialImage.
     * @param b: Image converted to BufferedImage
     * @param fileName: name of the file before conversion to BufferedImage
     */
    public SpecialImage(BufferedImage b, String fileName){
        this.img = b;
        this.ID = 0;
        this.name = fileName;
    }
}
