package shop.classes;

import shop.Shop;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CashDesk implements Runnable{
    private Employee employee;
    private Queue<Client> clientQueue;
    private Thread thread;
    //
    private Storage storage;
    public CashDesk() {
        this.clientQueue = new LinkedList<>();

    }

    public CashDesk(Employee employee) {
        this.employee = employee;
        this.clientQueue = new LinkedList<>();
    }

    public void addClient(Client client) {
        clientQueue.add(client);
    }

    public void startSelling(Storage storage) {
        this.storage = storage;
        System.out.println(employee.getName() + " started selling!");
        if(this.thread == null){
            this.thread = new Thread(this);
            this.thread.start();
            try {
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //double income = 0.0;
//        while(!clientQueue.isEmpty()){
//            Client current = clientQueue.peek();
//            if(current.getSumOfBag() <= current.getMoney()){
//                Shop.income += employee.sell(storage, current);
//            }else System.out.println("Not enough money!");
//            clientQueue.remove();
//        }
//        return storage;
    }

    @Override
    public String toString() {
        return "Cash desk with " + clientQueue.size() + " queue size.";
    }

    @Override
    public void run() {
        while(!clientQueue.isEmpty()){
            Client current = clientQueue.peek();
            if(current.getSumOfBag() <= current.getMoney()){
                Shop.income += employee.sell(storage, current);
            }else System.out.println("Not enough money!");
            clientQueue.remove();
        }
        //return storage;
    }
}
