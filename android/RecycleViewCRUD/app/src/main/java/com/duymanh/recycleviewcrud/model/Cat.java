package com.duymanh.recycleviewcrud.model;

public class Cat {
    private int img;
    private String name,describe;
    private Double price;


    public Cat() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Cat(int img, String name, String describe, Double price) {
        this.img = img;
        this.name = name;
        this.describe = describe;
        this.price = price;
    }
}
