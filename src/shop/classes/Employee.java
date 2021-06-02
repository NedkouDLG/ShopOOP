package shop.classes;

import shop.exceptions.NotEnoughQuantityException;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class Employee {
    private long id;
    private String name;
    private double salary;

    public Employee() {
    }

    public Employee(long id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double sell(Storage storage, Client current) {
        List<Product> shoppingBag = current.getShoppingBag();
        Bill bill = new Bill(this.name, LocalDate.now());
        double income = 0.0;
        for (Product currentProduct:shoppingBag) {
            try {
                storage.removeProduct(currentProduct);
                bill.addProduct(currentProduct);
                income += currentProduct.getSellPrice()* currentProduct.getCount();
            } catch (NotEnoughQuantityException e) {
                e.printStackTrace();
            }
        }
        writeBill(String.valueOf(bill.getBillId()),bill);
        //current.emptyBag();
        return income;
    }
    private void writeBill(String fileName, Bill bill){
        try(FileOutputStream fos = new FileOutputStream(fileName + ".bill");
            ObjectOutputStream outputStream = new ObjectOutputStream(fos);){
            outputStream.writeObject(bill);
            System.out.println("Bill saved!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
