package org.example.demo6;

import java.io.IOException;
import java.util.*;

public class Admin extends User {
    private TreeMap<String, Menus> menu;
    private PriorityQueue<Ordering> orderline;
    private List<Ordering> sales;
    private List<Review> reviews;
    private Scanner scanner;

    public Admin(String username ,PriorityQueue<Ordering> orderline,TreeMap<String , Menus> menu, List<Review> reviews ,Scanner scanner) {


        super(username,"adminn");
        this.menu = menu;
        this.orderline = orderline;
        this.sales = new ArrayList<>();
        this.reviews = reviews ;
        this.scanner = scanner;
        loadMenuFromFile();
        //   this.username = username;

    }
    public boolean login(String usernameis){
        return this.username.equals(usernameis);
    }
    public  void adminactions(){
        boolean logout = false;

        while(!logout){
            System.out.println("ADMIN MENU:");
            System.out.println("1. view menu ");
            System.out.println("2. add items");
            System.out.println("3. update items");
            System.out.println("4. remove item ");
            System.out.println("5 pending orders");
            System.out.println("6. update order status");
            System.out.println("7. generate report");
            System.out.println("8. logout?");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 -> menu(menu);
                case 2 -> item_add();
                case 3 -> update_itemmenu();
                case 4 -> remove_item( );
                case 5 -> pendingorderss();
                case 6 -> order_status(scanner);
                case 7 -> report_generate();
                case 8 -> {
                    System.out.println("logging out ...");
                    logout = true;
                }


                default -> System.out.println("invalid choice");

            }

        }
    }

    @Override
    public void menu(TreeMap<String, Menus> menu) {
        System.out.println("admin menu view :");
        for(Menus itemis : menu.values()){
            System.out.println(itemis);

        }
    }
    public void item_add(){
        // Menus itemis = new Menus(item, price ,categoryis , isavailable);
        // menu.put(item,itemis);
        System.out.println("enter item name:");
        String name = scanner.nextLine();
        System.out.println("enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("enter category: ");
        String category = scanner.nextLine();
        System.out.println("is item available (t/f):  ");
        boolean available = scanner.nextBoolean();
        Menus newItem = (new Menus(name , price , category , available));
        menu.put(name,newItem);
        saveMenuToFile();
        System.out.println("item added ");
    }
    public void update_itemmenu()
    {System.out.println("item to update :");
        String name = scanner.nextLine();
        Menus item = menu.get(name);

        if(item != null) {
            System.out.println("enter new price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            System.out.println("enter new category");
            String categoory = scanner.nextLine();
            System.out.println("item available? true/false  :");
            boolean available = scanner.nextBoolean();
            scanner.nextLine();
            //for(Menus item : menuitems){
            // if(item.getItemname().equalsIgnoreCase(name)){
            item.setAvailable(available);
            //item.search();
            item.setPrice(price);
            item.setCategory(categoory);
            saveMenuToFile();
            System.out.println("item added ");
        }
        else{
            System.out.println("item not found");
        }//return;






        //System.out.println("item cannot be found in menus");


    }


    public void remove_item() {
        System.out.println("enter item to remove");
        String name = scanner.nextLine();
        // boolean removed = menuitems.removeIf(itemis -> item.getItemname().equalsIgnoreCase(name));

        if( menu.remove(name) != null){
            System.out.println("item removed");

        }
        else{
            System.out.println("Item not found");
        }

    }
    public void special_request(){
        System.out.println("what speciall requests you demand");

    }
    public void pendingorderss() {
        if (orderline.isEmpty()) {
            System.out.println("no pending orders hereeeeeeeeeeee");

        }
        System.out.println("pending order are:");
        for (Ordering order : orderline) {
            System.out.println("order id : " + order.getId() + "------  status " + order.getStatus() + " -----total $ " + order.calculatetotal());
        }


    }
    public void report_generate(){
        System.out.println("generating report:");
        double total_sales = 0 ;
        int total_orders = 0 ;
        for(Ordering order : sales){
            if(order.getStatus().equals("completed")){
                total_sales += order.calculatetotal();
                total_orders++;
            }

        }System.out.println("totals sales : " + total_sales);
        System.out.println("total order " + total_orders);

    }
    public void refunds(){

    }
    public void order_status(Scanner scanner){
        System.out.println("enter id to update:  ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("enter new status: ");
        String status = scanner.nextLine();

        boolean found = false;
        Iterator<Ordering> iterator = orderline.iterator();
        while (iterator.hasNext()){
            Ordering order = iterator.next();
            if(order.getId()== id){
                order.setStatus(status);
                System.out.println("order status updated ");
                if("completed".equalsIgnoreCase(status)){
                    sales.add(order);
                    orderline.remove(order);
                }
                found = true ;
                break;

            }
        }
        if(!found){

            System.out.println("order cannot be found ");
        }
    }
    @Override
    public void order_history() {
        System.out.println("order history :");
        for(Ordering order :sales){
            System.out.println(order);
        }}


        private void loadMenuFromFile() {
        try {
            this.menu = MenuIOUtil.loadMenus();
            System.out.println("Menu loaded successfully from file.");
        } catch (IOException e) {
            System.out.println("Error loading menu data: " + e.getMessage());
        }
    }



    private void saveMenuToFile() {
        try {
            MenuIOUtil.saveMenus(menu);
            System.out.println("Menu saved successfully to file.");
        } catch (IOException e) {
            System.out.println("Error saving menu data: " + e.getMessage());
        }
    }
}
