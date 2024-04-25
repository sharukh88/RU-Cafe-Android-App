package com.example.project5;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * controller for store_orders
 * * @author Sharukh Khan, Hamad Naveed
 */

public class StoreOrderActivity extends AppCompatActivity {
    private static final StoreOrders store = new StoreOrders();
    private ListView storeOrders;
    private ArrayAdapter<Order> storeOrdersAdapter;

    /**
     Initializes the storeOrdersAdapter with a new ArrayAdapter that takes in the context of the activity,
     the layout for each item in the adapter, and a new ArrayList containing all the orders in the store.
     The storeOrdersAdapter is then set to the storeOrders ListView.
     */
    private void setupStoreOrdersAdapter() {
        storeOrdersAdapter = new ArrayAdapter<>(this, R.layout.store_orders, new ArrayList<>(store.getOrders()));
        storeOrders.setAdapter(storeOrdersAdapter);
    }

    /**
     Shows a confirmation dialog for canceling an order and cancels the order if the user confirms.
     @param order The order to cancel.
     */
    private void showCancelOrderConfirmation(Order order) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Canceling Order \uD83D\uDEAB");
        alert.setMessage("Are you sure you want to cancel this order?");
        alert.setPositiveButton("Yes", (dialog, which) -> {
            cancelOrder(order);
            Toast.makeText(this, "Order has been cancelled", Toast.LENGTH_LONG).show();
        }).setNegativeButton("No", (dialog, which) -> Toast.makeText(this, "Order has not been cancelled", Toast.LENGTH_LONG).show());
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    /**
     Sets up a listener for the ListView that displays the store's current orders. When an item in the list is clicked,
     this method calls the showCancelOrderConfirmation() method to prompt the user to confirm that they want to cancel
     the selected order.
     */
    private void setupStoreOrdersListViewListener() {
        storeOrders.setOnItemClickListener((adapterView, view, i, l) -> showCancelOrderConfirmation(storeOrdersAdapter.getItem(i)));
    }

    /**
     This method is called when the activity is first created.
     It sets the layout and finds the storeOrders ListView in the layout.
     It then calls the helper methods to set up the adapter and listener for the ListView.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
        storeOrders = findViewById(R.id.sOrders);

        setupStoreOrdersAdapter();
        setupStoreOrdersListViewListener();
    }

    /**
     Cancels the specified order by removing it from the store's orders and adapter's list.
     If the specified order is null, nothing is done.
     @param order The order to be cancelled
     */
    private void cancelOrder(Order order) {
        if (order == null) return;
        store.removeOrder(order.getNumOrder());
        storeOrdersAdapter.remove(order);
    }

    /**
     This method is used to place an order in the store. It creates a new instance of the Order class with the same values as the input order
     and adds it to the store.
     @param order The order to be placed
     */
    public static void placeOrder(Order order) {
        store.add(new Order(order));
    }
}