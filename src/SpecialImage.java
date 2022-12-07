package src;

import java.awt.image.BufferedImage;

public class SpecialImage{
    private BufferedImage img;
    private int ID;
    private String name;

    public BufferedImage getImg() {
        return img;
    }

    public String getName() {return name;}

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public SpecialImage(BufferedImage b, String fileName){
        this.img = b;
        this.ID = 0;
        this.name = fileName;
    }
}
