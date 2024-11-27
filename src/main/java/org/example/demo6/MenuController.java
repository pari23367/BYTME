//package org.example.demo6;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.cell.PropertyValueFactory;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URL;
//import java.util.List;
//import java.util.ResourceBundle;
//
//public class MenuController implements Initializable {
//    @FXML
//    private TableView<Menus> table;
//
//    @FXML
//    private TableColumn<Menus, Boolean> availability;
//
//    @FXML
//    private TableColumn<Menus, String> category;
//
//    @FXML
//    private TableColumn<Menus, Integer> item;
//
//    @FXML
//    private TableColumn<Menus, Double> price;
//
//    private ObservableList<Menus> list = FXCollections.observableArrayList();
//
//    private static final String JSON_FILE = "menu_data.json";
//
//    // Jackson ObjectMapper for JSON processing
//    private ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        // Initialize TableView columns
//        category.setCellValueFactory(new PropertyValueFactory<>("category"));
//        item.setCellValueFactory(new PropertyValueFactory<>("itemname"));
//        price.setCellValueFactory(new PropertyValueFactory<>("price"));
//        availability.setCellValueFactory(new PropertyValueFactory<>("available"));
//
//        // Load data from JSON file
//        loadMenuFromJson();
//
//        // Set items to the table
//        table.setItems(list);
//        System.out.println(list);  // To check if data is properly populated
//    }
//
//    // Method to load menu from JSON file
//    private void loadMenuFromJson() {
//        try {
//            // Check if the JSON file exists
//            File file = new File(JSON_FILE);
//            if (file.exists()) {
//                // Load the list from JSON file
//                List<Menus> menuItems = objectMapper.readValue(file, new TypeReference<List<Menus>>() {});
//                list.setAll(menuItems);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to save menu to JSON file
//    private void saveMenuToJson() {
//        try {
//            // Write the list to JSON file
//            objectMapper.writeValue(new File(JSON_FILE), list);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Example method for adding a new item (could be called via a button in the GUI)
//    public void addMenuItem(Menus newItem) {
//        list.add(newItem);
//        saveMenuToJson();  // Save changes to JSON
//    }
//
//    // Example method for removing an item (could be called via GUI actions)
//    public void removeMenuItem(Menus itemToRemove) {
//        list.remove(itemToRemove);
//        saveMenuToJson();  // Save changes to JSON
//    }
//}
package org.example.demo6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeMap;

public class MenuController {
    private Stage stage;
    private Scene scene ;
    private Parent root;
    @FXML
    private TableView<Menus> table;
    @FXML
    private TableColumn<Menus, String> item;
    @FXML
    private TableColumn<Menus, Double> price;
    @FXML
    private TableColumn<Menus, String> category;
    @FXML
    private TableColumn<Menus, Boolean> availability;


   // private TreeMap<String, Menus> menuData;
   private static final String MENU_FILE_PATH = "D:/menu_data.txt";
    @FXML
    public void initialize() {
        item.setCellValueFactory(new PropertyValueFactory<>("itemname"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        category.setCellValueFactory(new PropertyValueFactory<>("category"));
        availability.setCellValueFactory(new PropertyValueFactory<>("available"));
        loadMenuData();
}
    private void loadMenuData() {
        ObservableList<Menus> menuData = FXCollections.observableArrayList();

        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    String itemName = parts[0];
                    String category = parts[1];
                    double price = Double.parseDouble(parts[2]);
                    boolean available = Boolean.parseBoolean(parts[3]);

                    Menus menuItem = new Menus(itemName, price, category, available);
                    menuData.add(menuItem);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading menu data: " + e.getMessage());
        }

        // Set the items to the TableView
        table.setItems(menuData);
    }
    public  void switchto_back(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Adminmenu.fxml"));
        root = fxmlLoader.load();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

