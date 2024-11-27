//package org.example.demo6;
//
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import static org.testng.Assert.*;
//
//import java.util.*;
//
//public class MainTest {
//    private List<Admin> admins;
//    private List<Customer> customers;
//    private TreeMap<String, Menus> menuItems;
//    private PriorityQueue<Ordering> orderQueue;
//    private Scanner scanner;
//
//    @BeforeMethod
//    public void setUp() {
//        menuItems = new TreeMap<>();
//        admins = new ArrayList<>();
//        customers = new ArrayList<>();
//        orderQueue = new PriorityQueue<>(new OrderComparator());
//        scanner = new Scanner(System.in);
//
//        // Add a test admin and menu items
//        admins.add(new Admin("MCD_ADMIN1", orderQueue, menuItems, new ArrayList<>(), scanner));
//        menuItems.put("burger", new Menus("burger", 70.0, "fastfood", true));
//    }
//
//    @Test
//    public void testAdminLoginSuccess() {
//        assertTrue(adminloginTest("MCD_ADMIN1"), "Admin login should succeed with valid credentials.");
//    }
//
//    @Test
//    public void testAdminLoginFailure() {
//        assertFalse(adminloginTest("INVALID_ADMIN"), "Admin login should fail with invalid credentials.");
//    }
//
//    @Test
//    public void testCustomerSignup() {
//        customersignupTest("testUser", true);
//        assertFalse(customers.isEmpty(), "Customer list should not be empty after signup.");
//        assertEquals(customers.get(0).getUsername(), "testUser");
//    }
//
//    @Test
//    public void testMenuItemsLoaded() {
//        assertFalse(menuItems.isEmpty(), "Menu should not be empty after initialization.");
//        assertTrue(menuItems.containsKey("burger"), "Menu should contain a 'burger'.");
//    }
//
//    private boolean adminloginTest(String username) {
//        for (Admin admin : admins) {
//            if (admin.getUsername().equalsIgnoreCase(username)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private void customersignupTest(String username, boolean isVIP) {
//        Customer newCustomer = new Customer(username, isVIP);
//        customers.add(newCustomer);
//    }
//}
