package org.example.demo6;

import org.example.demo6.Menus;

import java.util.TreeMap;

public abstract class User {
    protected String username;
    protected  String type;
    public User(String username , String type){
        this.type = type ;
        this.username = username;
    }
    public String getUsername(){
        return username;
    }//getter for user name
    public String getType(){
        return type; //getter for vip or avg guy
    }
    public void login(){
        System.out.println(username + "logged in ");//common so abstract

    }//now diff for admin and user
    public abstract void menu(TreeMap<String , Menus> menuitems);
    public abstract void order_history();
}
