package org.example.demo6;

import java.util.List;

public class Ordering implements Comparable<Ordering> {
    private List<Cartt> items;
    private boolean isVip;
    private String status;
    private int id;
    private double totalPrice;
    private static int nextId = 1;

    public Ordering(List<Cartt> items, boolean isVip) {
        this.items = items;
        this.isVip = isVip;
        this.status = "received";
        this.id = nextId++;
        this.totalPrice = calculatetotal();
    }

    public Ordering(int id, boolean isVip, String status, double totalPrice) {
        this.id = id;
        this.isVip = isVip;
        this.status = status;
        this.totalPrice = totalPrice;
        nextId = Math.max(nextId, id + 1);
    }

    public double calculatetotal() {
        double total = 0;
        for (Cartt item : items) {
            total += item.getTotalprice();
        }
        return total;
    }

    public int getId() {
        return id;
    }

    public String getCustomerType() {
        return isVip ? "VIP" : "Regular";
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int compareTo(Ordering other) {
        if (this.isVip && !other.isVip) return -1;
        if (!this.isVip && other.isVip) return 1;
        return Integer.compare(this.id, other.id);
    }
    public boolean isVip() {
        return isVip;
    }

    @Override
    public String toString() {
        return "Order ID: " + id + ", Customer Type: " + getCustomerType() + ", Status: " + status + ", Total: " + totalPrice;
    }
}
