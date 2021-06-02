package shop;

import shop.classes.*;
import shop.statics.ProductCalculation;

import java.util.ArrayList;
import java.util.List;

public class Shop {
    private String name;
    private Storage storage;
    private List<Employee> employeeList;
    private List<CashDesk> cashDeskList;
    private double costs;
    public static double income;
    public Shop(){
        this.storage = new Storage();
        this.cashDeskList = new ArrayList<>();
        this.employeeList = new ArrayList<>();
        this.costs = 0.0;
        this.income = 0.0;
    }
    public Shop(String name, double foodIncrement, double nonFoodIncrement, double percentDiscount, int daysToExpire){
        this.name = name;
        this.storage = new Storage();
        this.employeeList = new ArrayList<>();
        this.cashDeskList = new ArrayList<>();
        this.costs = 0.0;
        this.income = 0.0;
        ProductCalculation.foodIncrement = foodIncrement;
        ProductCalculation.nonFoodIncrement = nonFoodIncrement;
        ProductCalculation.discount = percentDiscount;
        ProductCalculation.daysToExpire = daysToExpire;
    }
    public void addProduct(Product product){
        this.storage.addProduct(product);
        this.costs += product.getPrice() * product.getCount();
    }
    public void addEmployee(Employee employee){
        this.employeeList.add(employee);
        CashDesk cashDesk = new CashDesk(employee);
        cashDeskList.add(cashDesk);
        this.costs += employee.getSalary();
    }
    public void addCashDesk(CashDesk cashDesk){
        this.cashDeskList.add(cashDesk);
    }
    public List<CashDesk> getCashDeskList(){
        return this.cashDeskList;
    }
    //There is option to make copy of cashDesk and to not find it in cashDeskList!
    public void addClientToCashDesk(CashDesk cashDesk, Client client){
        for (CashDesk currentCashDesk: cashDeskList) {
            if(currentCashDesk == cashDesk){
               currentCashDesk.addClient(client);
            }
        }
    }
    public void startSelling(){
        for (CashDesk currentCashDesk:cashDeskList) {
            currentCashDesk.startSelling(this.storage);
        }
    }
    public Storage getStorage(){
        return this.storage;
    }
    public void showStorage(){
        this.storage.showStorage();
    }
    public void showCosts(){
        System.out.println("Costs: " + String.format("%.2f",this.costs));
    }
    public void showIncome(){
        System.out.println("Income: " + String.format("%.2f",this.income));
    }
    public void showProfit(){
        System.out.println("Profit: " + String.format("%.2f",(this.income - this.costs)));
    }
}
