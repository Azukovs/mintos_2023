package vending;

public class Product {
    public String name;
    public Money price;
    private int available;

    public Product(String name, Money price, int count) {
        this.name = name;
        this.price = price;
        this.available = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    public int getAvailable() {
        return this.available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }
}
