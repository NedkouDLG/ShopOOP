package shop.UI;

import shop.Shop;
import shop.classes.*;
import shop.types.CategoryType;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Shop shop;
    private Scanner input;
    public UserInterface(){
        input = new Scanner(System.in);
    }
    public void createStore(){
        System.out.print("Enter store name: ");
        String name = input.nextLine();
        System.out.print("Enter income percent for food: ");
        Double foodIncrement = Double.parseDouble(input.nextLine());
        System.out.print("Enter income percent for non-food: ");
        Double nonFoodIncrement = Double.parseDouble(input.nextLine());
        System.out.print("Enter discount percent for expiring products: ");
        Double discount = Double.parseDouble(input.nextLine());
        System.out.print("Enter days to expire: ");
        Integer days = Integer.parseInt(input.nextLine());
        shop = new Shop(name,foodIncrement,nonFoodIncrement,discount,days);
    }
    public void addProductToStorage(){
        System.out.println("Adding a product to store...");
        System.out.print("Enter product id: ");
        Long id = Long.parseLong(input.nextLine());
        System.out.print("Enter product name: ");
        String name = input.nextLine();
        System.out.print("Enter delivery price: ");
        Double price = Double.parseDouble(input.nextLine());
        System.out.print("Enter a category of product - food or nonfood: ");
        CategoryType categoryType = null;
        while(categoryType == null) {
            String category = input.nextLine();
            switch (category) {
                case "food":
                    categoryType = CategoryType.FOOD;
                    break;
                case "nonfood":
                    categoryType = CategoryType.NON_FOOD;
                    break;
                default:
                    System.out.print("Enter food or nonfood: ");
            }
        }
        System.out.print("Enter count: ");
        Integer count = Integer.parseInt(input.nextLine());
        System.out.print("Enter expire year: ");
        Integer year = Integer.parseInt(input.nextLine());
        System.out.print("Enter expire month: ");
        Integer month = Integer.parseInt(input.nextLine());
        System.out.print("Enter expire day: ");
        Integer day = Integer.parseInt(input.nextLine());

        LocalDate expireDate = LocalDate.of(year, month, day);

        Product baseProduct = new Product(id,name,price,expireDate,categoryType);
        baseProduct.setCount(count);
        shop.addProduct(baseProduct);
        //System.out.println(baseProduct);
        System.out.println("Product is added to storage!");
    }
    public void addCashier(){
        System.out.println("Add cashier...");
        System.out.print("Enter cashier name: ");
        String name = input.nextLine();
        System.out.print("Enter cashier id: ");
        Long id = Long.parseLong(input.nextLine());
        System.out.print("Enter cashier salary: ");
        Double salary = Double.parseDouble(input.nextLine());
        Employee cashier = new Employee(id, name, salary);
        shop.addEmployee(cashier);

        System.out.println("Cashier is created!");
    }
    private List<Product> getMenu(){
        //List<Product> menu = shop.getStorage().getProductList();
        List<Product> menu = new ArrayList<>();

        for (Product current:shop.getStorage().getProductList()) {
            if(current.getExpireDate().isAfter(LocalDate.now())){
                menu.add(current);
            }
        }
        int br = 0;
        System.out.println("Choose products to add to client bag: ");
        for (Product current:menu) {
            br++;
            System.out.println(br + ". " + current.getSellProductInfo());

        }
        return menu;
    }
    public void addClient(){
        System.out.println("Adding client...");
        System.out.print("Add money of client: ");
        Double money = Double.parseDouble(input.nextLine());
        Client client = new Client(money);
        List<Product> menu = getMenu();
        String str = "";
        System.out.println("To stop adding type 'end' !");
        while(true){
            System.out.print("Enter number of product or 'end': ");
            str = input.nextLine();
            if(str.equals("end"))
                break;

            int number = Integer.parseInt(str);
            System.out.print("Enter count: ");
            int count = Integer.parseInt(input.nextLine());
            Product currentProduct = new Product(menu.get(number-1));
            currentProduct.setCount(count);
            client.addProduct(currentProduct);
        }
        System.out.println("Choose a cash desk to wait.");
        printCashDesks();
        System.out.print("Enter cash desk number: ");
        int number = Integer.parseInt(input.nextLine()) - 1;
        shop.addClientToCashDesk(shop.getCashDeskList().get(number),client);
        System.out.println("Client added successfully!");
//        CashDesk minQueueCashDesk = new CashDesk();
//        boolean added = false;
//        for (CashDesk current:store.) {
//            if(current.getClients() == null){
//                added = true;
//                current.addClient(client);
//                System.out.println("Client successfully added to cash desk!");
//                break;
//            }else{
//                if(minQueueCashDesk.getClients().size() > current.getClients().size()){
//                    minQueueCashDesk = current;
//                }
//            }
//        }
//        if(!added){
//            minQueueCashDesk.addClient(client);
//        }
    }
    public void showStorage(){
        shop.showStorage();
    }
    public void startSelling(){
        shop.startSelling();
    }
    private void printCashDesks(){
        int br = 0;
        for (CashDesk current: shop.getCashDeskList()) {
            br++;
            System.out.println(br + ". " + current);
        }

    }
    public void showProfitCostsAndIncome(){
        shop.showCosts();
        shop.showIncome();
        shop.showProfit();
    }
    private void printFiles(){
        File file = new File(".");
        File[] files = file.listFiles();
        for (File currentFile:files) {
            if(currentFile.isFile()){
                System.out.println(currentFile.getName());
            }
        }
    }
    public void readBill(){
        printFiles();
        System.out.print("Type the name of the bill you want to read: ");
        String fileName = input.nextLine();
        Bill bill = null;
        try(FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fis);){
            bill = (Bill) objectInputStream.readObject();
            System.out.println(bill);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
