package org.example.demo6;

import java.io.*;
import java.util.PriorityQueue;

public class OrderIOUtil {
    private static final String ORDER_FILE = "D:/demo6/src/pending.txt";

    // Save pending orders to a text file
    public static void savePendingOrders(PriorityQueue<Ordering> orders) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ORDER_FILE))) {
            for (Ordering order : orders) {
                // Format: OrderID|CustomerType|Status|TotalPrice
                writer.write(order.getId() + "|" + (order.isVip() ? "VIP" : "Regular") + "|" + order.getStatus() + "|" + order.calculatetotal());
                writer.newLine();
            }
        }
    }

    // Load pending orders from a text file
    public static PriorityQueue<Ordering> loadPendingOrders() throws IOException {
        PriorityQueue<Ordering> orders = new PriorityQueue<>(new OrderComparator());
        File file = new File(ORDER_FILE);
        if (!file.exists()) {
            return orders; // Return empty queue if the file does not exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ORDER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    int orderId = Integer.parseInt(parts[0]);
                    boolean isVip = "VIP".equalsIgnoreCase(parts[1]);
                    String status = parts[2];
                    double totalPrice = Double.parseDouble(parts[3]);

                    Ordering order = new Ordering(orderId, isVip, status, totalPrice);
                    orders.add(order);
                }
            }
        }
        return orders;
    }
}
