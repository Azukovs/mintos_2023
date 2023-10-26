package vending;

import vending.interfaces.IVendingMachine;

import static java.lang.Double.parseDouble;

public class VendingMachine implements IVendingMachine {
    private final String manufacturer;
    private Money amount;
    private Product[] products = null;
    private Product selectedProduct = null;
    private Product outputTray = null;
    private Money remainderTray = null;
    private String display = "";

    public VendingMachine(String manufacturer) {
        this.manufacturer = manufacturer;
        this.amount = new Money();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Money getAmount() {
        return amount;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Money insertCoin(Money amount) {
        if (amount.getEuros() < 0 || amount.getCents() < 0) {
            this.display = "Coin not recognized";
            return null;
        }
        this.amount.add(amount);
        return this.amount;
    }

    public Money insertCoin(String amount) {
        return insertCoin(new Money(parseDouble(amount)));
    }

    public Money returnMoney() {
        this.remainderTray = amount;
        return this.remainderTray;
    }

    public Product buy(int productNumber) {
        Money productPrice = this.selectedProduct.getPrice();
        if (amount.asDouble() < productPrice.asDouble()) {
            this.display = "Not enough money, please add the remainder";
            return null;
        }

        this.remainderTray = amount.getDifference(this.selectedProduct.getPrice());
        this.outputTray = this.selectedProduct;
        this.amount.remove(productPrice);
        return this.selectedProduct;
    }


    public Product selectProduct(int productNumber) {
        if (products[productNumber - 1].getAvailable() < 1) {
            this.display = "Selected product unavailable, please choose another";
            return null;
        }
        this.selectedProduct = products[productNumber - 1];
        return this.selectedProduct;
    }

    public Product getOutputTrayProduct() {
        return this.outputTray;
    }

    public Money getRemainderTray() {
        return remainderTray;
    }

    public String getDisplay() {
        return this.display;
    }

}
