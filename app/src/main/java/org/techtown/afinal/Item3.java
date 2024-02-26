package org.techtown.afinal;

public class Item3 {

    private String name;
    private String option1, option2, option3;
    private String size, cup;
    private String image;
    private int count, price;
    private String key;
    private int type; // 아이템의 타입

    public Item3() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getCup() {
        return cup;
    }

    public void setCup(String cup) {
        this.cup = cup;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Item3(String name, String option1, String option2, String option3, String size, String cup,
                 String image, int count, int price, String key, int type) {

        this.name = name;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.size = size;
        this.cup = cup;
        this.image = image;
        this.count = count;
        this.price = price;
        this.key = key;
        this.type = type;
    }
}