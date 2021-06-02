package shop.classes;

import shop.statics.ProductCalculation;
import shop.types.CategoryType;

import java.io.Serializable;
import java.time.LocalDate;

public class Product implements Serializable {
    private long productId;
    private String name;
    private double price;
    private LocalDate expireDate;
    private double sellPrice;
    private CategoryType category;
    private int count;

    public Product() {
    }

    public Product(long productId, String name, double price, LocalDate expireDate, CategoryType category) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.expireDate = expireDate;
        this.category = category;
        this.setSellPrice();
    }

    public Product(Product product) {
        this.productId = product.getProductId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.expireDate = product.getExpireDate();
        this.category = product.getCategory();
        this.sellPrice = product.getSellPrice();
    }

    public long getProductId() {
        return this.productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getExpireDate() {
        return this.expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }
    public double getSellPrice() {
        return this.sellPrice;
    }

    private void setSellPrice() {
        if(category == CategoryType.FOOD)
            this.sellPrice = this.price + (this.price * ProductCalculation.foodIncrement/100);
        else
            this.sellPrice = this.price + (this.price * ProductCalculation.nonFoodIncrement/100);

        LocalDate dateForDiscount = LocalDate.now().plusDays(ProductCalculation.daysToExpire);
        if(expireDate.isBefore(dateForDiscount)){
            this.sellPrice = this.sellPrice - (this.sellPrice*ProductCalculation.discount/100);
        }
    }
    public CategoryType getCategory() {
        return this.category;
    }

    public void setCategory(CategoryType category) {
        this.category = category;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public String getSellProductInfo(){
        return this.name + " - " + this.expireDate + " - " + this.sellPrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", expireDate=" + expireDate +
                ", sellPrice=" + sellPrice +
                ", category=" + category +
                ", count=" + count +
                '}';
    }
}
