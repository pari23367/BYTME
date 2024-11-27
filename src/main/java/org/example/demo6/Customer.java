package org.example.demo6;

import java.io.IOException;
import java.util.*;

public  class Customer extends User {
    private TreeMap<String, Menus> menu;
    private List<Cartt> cart ;
    private List<Ordering> orderhistory;
    private boolean vip ;
    public Customer(String username, boolean vip) {
        super(username,   vip ? "VIP" : "REGULAR");
        this.cart = new ArrayList<>();
        this.menu = menu;
        this.orderhistory = new ArrayList<>();
        this.vip = vip;
        loadMenuFromFile();

    }
    public  void customeractions(Scanner scanner , PriorityQueue<Ordering> orderhistory , TreeMap<String,Menus> menuitems , List<Review> reviews){
      boolean logout = false ;
      while(!logout){
          System.out.println("\n customer menu");
          System.out.println("1. browse menu");
          System.out.println("2. view cart");
          System.out.println("3. checkout");
          System.out.println("4. Track orders");
          System.out.println("5. view order history");
          System.out.println("6. logout ");
          int choice = scanner.nextInt();
          scanner.nextLine();
          switch (choice){
              case 1 -> menu(menuitems );
              case 2 -> viewcart();
              case 3 -> checkout(orderhistory);
              case 4 -> trackorder();
              case 5 -> order_history();
              case 6 -> logout = true ;
              default ->  System.out.println("invalid haah");

          }
      }
    }

    public void order_history() {
        System.out.println("order history--");
        for(Ordering order : orderhistory){
            System.out.println(order);
        }
    }

    private void trackorder() {
        System.out.println("order tracking :");
        for(Ordering order : orderhistory){
            System.out.println(order);
        }
    }

    private void viewcart() {
        if(cart.isEmpty()){
            System.out.println("ur cart is empty");
            return;
        }
        System.out.println("cart items:");
        for(Cartt item : cart){
            System.out.println(item);
        }
    }

  //  @Override
  //  public void menu(TreeMap<String,Menus> menu) {
 //       System.out.println("customer menu view:");
  //      for(Menus itemis : menu.values()){
    //        if(itemis.isAvailable()){
  //              System.out.println(itemis);
      //      }
        //}

    //}
    public void menu(TreeMap<String, Menus> menuitems ){
        try{
            menuitems = MenuIOUtil.loadMenus();

            System.out.println("menu:");
            for (Menus item : menuitems.values()) {
                if (item.isAvailable()) {
                    System.out.println(item);
                }
            }
        }catch (IOException e) {
            System.out.println("Error loading menu: " + e.getMessage());
        }
        System.out.println("\n optionsss");
        System.out.println("1. add item to cart");
        System.out.println("2. back menu");
        int choice = new Scanner(System.in ).nextInt();
        //scanner.nextLine();
        if(choice == 1 ){
            Scanner scanner = new Scanner(System.in);
            System.out.println("enter item to add");
            String itemname = scanner.nextLine();
            Menus menuitem = menuitems.get(itemname);
            if(menuitem != null && menuitem.isAvailable()){
                System.out.println("enter quantity: ");
                int quantity = scanner.nextInt();
                addingtocart(menuitem,quantity);
                System.out.println("item added to cart");

            }else{
                System.out.println("not available");
            }
        }
    }

    private void addingtocart(Menus itemis , int quantity){
        Cartt cartitem = new Cartt(itemis, quantity);
        cart.add(cartitem);
    }
     public void checkout(PriorityQueue<Ordering> ordersis){
        if(cart.isEmpty()){
            System.out.println("cart is empty");
            return;
        }
        Ordering order = new Ordering(cart, vip);
        ordersis.add(order);
        orderhistory.add(order);
        cart.clear();
        System.out.println("checkout done" + order.getId());
     }
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
