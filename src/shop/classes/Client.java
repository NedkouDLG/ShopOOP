package shop.classes;

import shop.interfaces.StorageInterface;

import java.util.ArrayList;
import java.util.List;

public class Client implements StorageInterface {
    private double money;
    private List<Product> productList;
    public Client(){
        this.money = 0.0;
        this.productList = new ArrayList<>();
    }
    public Client(double salary){
        this.money = salary;
        this.productList = new ArrayList<>();
    }
    @Override
    public void addProduct(Product product) {
        this.productList.add(product);
    }

    public void removeProduct(Product product) {
        for (Product currentProduct:this.productList) {
            if(currentProduct.getProductId() == product.getProductId()){
                this.productList.remove(currentProduct);
            }
        }
    }
    public double getSumOfBag(){
        double sum = 0.0;
        for (Product currentProduct:this.productList) {
            sum += currentProduct.getSellPrice() * currentProduct.getCount();
        }
        return sum;
    }
    public List<Product> getShoppingBag() {
        return this.productList;
    }

    public double getMoney() {
        return this.money;
    }

    public void emptyBag() {
        for (Product currentProduct:this.productList) {
            productList.remove(currentProduct);
        }
    }
}
