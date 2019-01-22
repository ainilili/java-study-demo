package org.nico.java8.test;

public class Book{
    String name;
    int price;
    public Book(String name, int price) {
        super();
        this.name = name;
        this.price = price;
    }
    @Override
    public String toString() {
        return "Book [name=" + name + ", price=" + price + "]";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }
}