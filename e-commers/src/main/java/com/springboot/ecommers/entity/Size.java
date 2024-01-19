package com.springboot.ecommers.entity;

public class Size {
    private String name;
    private int quantity;

    public Size(){

    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
