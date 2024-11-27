package org.example.demo6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.PriorityQueue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.event.ActionEvent;

import java.io.IOException;

public class PendingsOrderController {
    private Stage stage;
    private Scene scene ;
    private Parent root;
    @FXML
    private TableView<OrderTableRow> pendingOrdersTable; // Use OrderTableRow for TableView
    @FXML
    private TableColumn<OrderTableRow, Integer> orderIdColumn;
    @FXML
    private TableColumn<OrderTableRow, String> customerTypeColumn;
    @FXML
    private TableColumn<OrderTableRow, String> statusColumn;
    @FXML
    private TableColumn<OrderTableRow, Double> totalPriceColumn;
    @FXML
    private TextField statusField;
    @FXML
    private Button updateStatusButton;
  //  @FXML
  //  private Button viewDetailsButton;
    @FXML
    private Button backButton;

    private PriorityQueue<Ordering> pendingOrders;
    private static final String PENDING_FILE_PATH = "D:/pending.txt";


    public void initialize() {
        // Set up columns
        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerTypeColumn.setCellValueFactory(new PropertyValueFactory<>("customerType"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        totalPriceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        // Load pending orders
        loadPendingOrders();

        // Set button actions
        updateStatusButton.setOnAction(event -> updateOrderStatus());
      //  viewDetailsButton.setOnAction(event -> viewOrderDetails());
       // backButton.setOnAction(event -> navigateBack());
    }

    private void loadPendingOrders() {
        try {
            // Load orders from file
            pendingOrders = OrderIOUtil.loadPendingOrders();

            // Convert to ObservableList of OrderTableRow
            ObservableList<OrderTableRow> tableRows = FXCollections.observableArrayList();
            for (Ordering order : pendingOrders) {
                tableRows.add(new OrderTableRow(order));
            }

            // Set items in TableView
            pendingOrdersTable.setItems(tableRows);
        } catch (IOException e) {
            System.out.println("Error loading pending orders: " + e.getMessage());
        }
    }

    private void updateOrderStatus() {
        OrderTableRow selectedRow = pendingOrdersTable.getSelectionModel().getSelectedItem();
        if (selectedRow != null) {
            for (Ordering order : pendingOrders) {
                if (order.getId() == selectedRow.getId()) {
                    String newStatus = statusField.getText();
                    order.setStatus(newStatus); // Update original object
                    statusField.clear();

                    // Refresh TableView
                    loadPendingOrders();

                    // Save updated orders to file
                    try {
                        OrderIOUtil.savePendingOrders(pendingOrders);
                        System.out.println("Order status updated and saved.");
                    } catch (IOException e) {
                        System.out.println("Error saving updated order status: " + e.getMessage());
                    }
                    break;
                }
            }
        }
    }

    private void viewOrderDetails() {
        OrderTableRow selectedRow = pendingOrdersTable.getSelectionModel().getSelectedItem();
        if (selectedRow != null) {
            System.out.println("Order Details: " + selectedRow.getId() + " - " + selectedRow.getStatus());
        }
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
