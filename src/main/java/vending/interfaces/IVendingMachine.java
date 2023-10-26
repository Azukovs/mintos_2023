package vending.interfaces;

import vending.Money;
import vending.Product;

public interface IVendingMachine {
    String getManufacturer();
    Money getAmount();
    Product[] getProducts();
    void setProducts(Product[] products);
    Money insertCoin(Money amount);
    Money returnMoney();
    Product buy(int productNumber);
}

