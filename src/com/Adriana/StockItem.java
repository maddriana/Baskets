package com.Adriana;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityStock = 0;
    private int reserved=0;

    public StockItem(String name, double price) {
        this.name = name;
        this.price = price;
        this.quantityStock =0;
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityStock;
    }

    public void setPrice(double price) {
        if (price > 0.00) this.price = price;
    }

    public void setQuantityStock(int quantityStock) {
        this.quantityStock = quantityStock;
    }

    public void adjustStock(int quantity) {
        int newQuantity = this.quantityStock + quantity;
        if (newQuantity >= 0) this.quantityStock = newQuantity;

    }

    public int getReserved() {
        return reserved;
    }

    public boolean setReserved(int reserved) {
        int newReserved = this.reserved + reserved;
        int newQuantity = this.quantityStock - newReserved;
        if (newQuantity >= 0) {this.reserved = newReserved; return true;}
        return false;
    }

    public void cleanReserved(){
        this.reserved=0;
    }

    @Override
    public boolean equals(Object obj) {
        System.out.println("Entering StockItem.equals");
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        String objName = ((StockItem) obj).getName();
        return this.name.equals(objName);
    }


    @Override
    public int compareTo(StockItem a){
        if(this==a)return 0;
        if(a != null){
            return this.name.compareTo(a.getName());
        }
        throw new NullPointerException();
    }

    @Override
    public String toString() {
        return this.name +": price " + String.format("%.02f", this.price);
    }
}
