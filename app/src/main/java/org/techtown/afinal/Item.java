package org.techtown.afinal;

public class Item {

    private String name;
    private String price1;
    private int image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Item(String name, String price1, int image) {
        this.name = name;
        this.price1 = price1;
        this.image = image;
    }
}
