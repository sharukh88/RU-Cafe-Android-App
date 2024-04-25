package com.example.project5;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.Collection;
/**
 * controller for activity_basket
 * @author Sharukh Khan, Hamad Naveed
 */
public class OrderBasketActivity extends AppCompatActivity {

    private static Order order = new Order();
    private TextView subTotal, tax, total;
    private ListView listOrder;
    private ArrayAdapter<MenuItem> adapter;
    private Button placeOrder;

    /**
     controller for activity_basket
     This method sets up the view for the basket activity, and handles user interactions such as placing and removing orders.
     @param savedInstanceState a Bundle object containing the activity's previously saved state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        views();

        adapter = new ArrayAdapter<>(this, R.layout.store_orders, order.getItems());
        listOrder.setAdapter(adapter);

        placeOrder.setOnClickListener(view -> {
            if(adapter.isEmpty()) {
                Toast.makeText(view.getContext(), " Please order something!", Toast.LENGTH_LONG).show();
                return;
            }
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("Placing order \uD83D\uDCDD");
            alert.setMessage("Are you sure you want to place this order?");
            alert.setPositiveButton("yes", (dialog, which) -> {
                Order existingOrder = order;
                existingOrder.getItems();
                StoreOrderActivity.placeOrder(existingOrder);
                resetOrderCost();
                Toast.makeText(view.getContext(), " Order has been placed! \uD83C\uDF89", Toast.LENGTH_SHORT).show();
            }).setNegativeButton("no", (dialog, which) -> Toast.makeText(view.getContext(),
                    " Order has not been placed", Toast.LENGTH_LONG).show());
            AlertDialog dialog = alert.create();
            dialog.show();
        });

        listOrder.setOnItemClickListener((adapterView, view, i, l) -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("Remove item \uD83D\uDEAB");
            alert.setMessage("Do you want to remove this item?");
            alert.setPositiveButton("yes", (dialog, which) -> {
                cancel(adapter.getItem(i));
                updateCost();
                Toast.makeText(view.getContext(), " Item has been removed \uD83D\uDE14", Toast.LENGTH_LONG).show();
            }).setNegativeButton("no", (dialog, which) -> Toast.makeText(view.getContext(),
                    " Item has not been removed", Toast.LENGTH_LONG).show());
            AlertDialog dialog = alert.create();
            dialog.show();
        });

        updateCost();
    }

    /**
     Adds menu items to the order list if they are valid.
     @param orderItems The menu items to add to the order list.
     */
    public static void addToOrder(MenuItem... orderItems)
    {
        for (MenuItem orderItem : orderItems)
        {
            if (orderItem != null && isValidMenuItem(orderItem))
            {
                order.add(orderItem);
            }
        }
    }

    /**
     Checks if a given MenuItem is valid.
     @param item the MenuItem to be checked
     @return true if the MenuItem is valid, false otherwise
     */
    private static boolean isValidMenuItem(MenuItem item)
    {
        return true; // or false if the item is not valid
    }

    /**
     Updates the subtotal, tax, and total cost of the order in the UI.
     */
    private void updateCost()
    {
        subTotal.setText(MainActivity.formatPrice(order.getSubTotal()));
        tax.setText(MainActivity.formatPrice(order.getTax()));
        total.setText(MainActivity.formatPrice(order.getTotal()));
    }

    /**
     Resets the current order by creating a new order object, clearing the adapter, and updating the cost display.
     */
    public void resetOrderCost()
    {
        order = new Order();
        adapter.clear();
        updateCost();
    }

    /**
     Initializes the views used in the Basket activity
     */
    private void views()
    {
        subTotal = findViewById(R.id.orderSubTotal5);
        tax = findViewById(R.id.salesTax);
        total = findViewById(R.id.total);
        listOrder = findViewById(R.id.orderList);
        placeOrder = findViewById(R.id.placeOrderButton);
    }

    /**
     * cancel order
     * @param item
     */
    public void cancel(MenuItem item)
    {
        if(item == null) return;
        order.remove(item);
        adapter.remove(item);
    }

    /**
     Adds a collection of menu items to the current order.
     @param orderItems the collection of menu items to be added to the order
     */
    public static void addToOrder(Collection<? extends MenuItem> orderItems) {
        for (MenuItem orderItem : orderItems) {
            order.add(orderItem);
        }
    }

}