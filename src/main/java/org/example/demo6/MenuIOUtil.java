package org.example.demo6;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MenuIOUtil {
    private static final String MENU_FILE = "D:/menu_data.txt";

    // Save menu items to a text file
    public static void saveMenus(TreeMap<String, Menus> menu) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (Menus menuItem : menu.values()) {
                // Format: ItemName|Category|Price|Availability
                writer.write(menuItem.getItemname() + "|" + menuItem.getCategory() + "|" + menuItem.getPrice() + "|" + menuItem.isAvailable());
                writer.newLine();
            }
        }
    }

    // Load menu items from a text file
    public static TreeMap<String, Menus> loadMenus() throws IOException {
        TreeMap<String, Menus> menu = new TreeMap<>();
        File file = new File(MENU_FILE);
        if (!file.exists()) {
            return menu; // Return an empty menu if the file does not exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String itemName = parts[0];
                    String category = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    boolean available = Boolean.parseBoolean(parts[3]);
                    Menus menuItem = new Menus(itemName, price, category, available);
                    menu.put(itemName, menuItem);
                }
            }
        }
        return menu;
    }
}
