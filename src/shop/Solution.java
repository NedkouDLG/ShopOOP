package shop;

import shop.UI.UserInterface;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        userInterface.createStore();
        userInterface.addCashier();
        userInterface.addProductToStorage();
        boolean end = true;
        while(end){
            System.out.println("Choose option:");
            System.out.println("1. Add product to store");
            System.out.println("2. Add cashier to store");
            System.out.println("3. Add client to store");
            System.out.println("4. Get profit and costs of store");
            System.out.println("5. Start cash desks to sell");
            System.out.println("6. Show storage");
            System.out.println("7. Show and read bills");
            System.out.println("8. End program");
            Scanner input = new Scanner(System.in);
            int option = Integer.parseInt(input.nextLine());
            switch (option){
                case 1:
                    userInterface.addProductToStorage();
                    break;
                case 2:
                    userInterface.addCashier();
                    break;
                case 3:
                    userInterface.addClient();
                    break;
                case 4:
                    userInterface.showProfitCostsAndIncome();
                    break;
                case 5:
                    userInterface.startSelling();
                    break;
                case 6:
                    userInterface.showStorage();
                    break;
                case 7:
                    userInterface.readBill();
                    break;
                case 8:
                    end = false;
                    break;
            }
        }
    }
}
