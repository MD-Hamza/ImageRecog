package src;

import java.awt.image.BufferedImage;

public class SpecialImage{
    private BufferedImage img;
    private int ID;

    public BufferedImage getImg() {
        return img;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public SpecialImage(BufferedImage b){
        this.img = b;
        this.ID = 0;
    }
}
