package org.example.demo6;

public class OrderTableRow {
    private final int id;
    private final String customerType;
    private final String status;
    private final double totalPrice;

    public OrderTableRow(Ordering order) {
        this.id = order.getId();
        this.customerType = order.getCustomerType();
        this.status = order.getStatus();
        this.totalPrice = order.getTotalPrice();
    }

    public int getId() {
        return id;
    }

    public String getCustomerType() {
        return customerType;
    }

    public String getStatus() {
        return status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
