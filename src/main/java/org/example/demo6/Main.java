package org.example.demo6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Main extends Application {
    private static boolean launchGUI = false;
    @Override
    public void start(Stage stage) throws IOException {
        if(launchGUI){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/example/demo6/Login.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
        }
    }

    public static void main(String[] args) {
        TreeMap<String, Menus> menuitems = new TreeMap<>();
        try {
            menuitems = MenuIOUtil.loadMenus(); // Load menu once
        } catch (IOException e) {
            System.out.println("Error loading initial menu: " + e.getMessage());
        }
        List<Admin> admins = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        PriorityQueue<Ordering> orderqueue = new PriorityQueue<>(new OrderComparator());
       // TreeMap<String, Menus> menuitems = new TreeMap<>();
        List<Review> reviews = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        admins.add(new Admin("MCD_ADMIN1", orderqueue , menuitems , reviews, scanner));
        //addtodefinedmenu(menuitems);
//        menuitems.put("burger" , new Menus("burgor" , 70, "fastfood", true));
//        menuitems.put("pizze" , new Menus("pizza",250 , "fastfood", true));
//        menuitems.put("rajma", new Menus("rajma", 80 , "lunch " , true));
//        menuitems.put("chole" , new Menus("chole chawal ", 120 , "dinner" , false));
        // Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        //toh idhr strt hota hai loop for the main

        while(!exit ){
            System.out.println("HI sir / mam - welcome to byte me^^");
            System.out.println("1. Admin login");
            System.out.println("2. Customer login");
            System.out.println("3. Customer sign - up ");
            System.out.println("4. launch gui");
            System.out.println("5. exit ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice){
                case 1 -> adminlogin(admins,scanner);
                case 2 -> customerlogin(customers , scanner , orderqueue,menuitems , reviews);
                case 3 -> customersignup(customers , scanner);
                case 4 -> launchloginscreen();
                case 5 -> {
                    System.out.println("exiting");
                    exit = true ;
                }default -> System.out.println("invalidddddddd");
            }

        }
        scanner.close();


    }
    private static void launchloginscreen() {
        System.out.println("Launching GUI for Login Screen...");
        launchGUI = true;
        new Thread(() -> Application.launch(Main.class)).start();
    }

    private static void customersignup(List<Customer> customers, Scanner scanner) {
        System.out.println("enter new name ");
        String username = scanner.nextLine();
        System.out.println("are you a VIP HUH? (true /false): ");
        boolean isVIP = scanner.nextBoolean();
        scanner.nextLine();
        Customer newcustomer = new Customer(username , isVIP);
        customers.add(newcustomer);
        System.out.println("signup hogya");

    }

    private static void customerlogin(List<Customer> customers, Scanner scanner, PriorityQueue<Ordering> orderqueue,TreeMap<String , Menus> menuitem , List<Review> reviews) {

        System.out.println("enter customer  user name ");
        String usernameis= scanner.nextLine();
        for(Customer customer : customers){
            if(customer.getUsername().equals(usernameis)){
                System.out.println("customer login successss");
                customer.customeractions(scanner,orderqueue,menuitem,reviews);
                return;
            }
        }System.out.println("customer not found ");


    }
    //idhr admimnlogin krlo ek hi mcd_admin1 , ek hi kra hai
    private static void adminlogin(List<Admin> admins, Scanner scanner) {
        System.out.println("enter your admin username :");
        String usernameis = scanner.nextLine();
        for(Admin admin : admins){
            if(admin.getUsername().equalsIgnoreCase(usernameis)){
                System.out.println("admin login successsss");
                admin.adminactions();
                return;
            }
        }System.out.println("invalid admin credentialssss");
    }

}
