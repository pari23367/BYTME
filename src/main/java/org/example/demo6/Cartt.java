package org.example.demo6;

public class Cartt {
    private Menus item;
    private int quantity;
    public Cartt(Menus item , int quantity){
        this.item  = item;
        this.quantity = quantity;
    }
    public String getItemname(){
        return item.getItemname();
    }
    public  double getTotalprice(){
        return item.getPrice() * quantity;
    }

    public String toString(){
        return item.getItemname() + "x" + quantity + "= "  + getTotalprice();
    }
}
