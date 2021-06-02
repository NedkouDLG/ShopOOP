package shop.classes;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Bill implements Serializable {
    private static int billId = 0;
    private List<Product> productList;
    private String employeeName;
    private LocalDate date;

    public Bill() {
    }

    public Bill(String employeeName, LocalDate date) {
        billId++;
        this.employeeName = employeeName;
        this.productList = new ArrayList<>();
        this.date = date;
    }

    public Bill(List<Product> productList, String employeeName, LocalDate date) {
        billId += 1;
        this.productList = productList;
        this.employeeName = employeeName;
        this.date = date;
    }

    public static int getBillId() {
        return billId;
    }

    public static void setBillId(int billId) {
        Bill.billId = billId;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProduct(Product product){
        this.productList.add(product);
    }
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public double getBillSum(){
        double sum = 0.0;
        for (Product currentProduct: this.productList) {
            sum += currentProduct.getSellPrice() * currentProduct.getCount();
        }
        return sum;
    }

    @Override
    public String toString() {
        String result = "      Bill     \n"+
                " Cashier: " + getEmployeeName()+"\n\n";
        double price = 0.0;
        for (Product current:this.productList) {
            price = current.getSellPrice() * current.getCount();
            String temp = current.getName() + " = "+current.getSellPrice()+" x "+ current.getCount() + " = " + String.format("%.2f",price) + "\n";
            result += temp;
        }
        result += "Price to pay: " + String.format("%.2f",price) + "\n";
        result += "Date: " + LocalDate.now();
        return result;
    }
}
