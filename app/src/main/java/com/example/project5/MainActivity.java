package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

/**
 * controller for the main menu. it has all of the button to coffee, donut etc.
 * * @author Sharukh Khan, Hamad Naveed
 * API we used is 33. AVD we used is Pixel_3a
 */
public class MainActivity extends AppCompatActivity {

    private ImageButton donuts, coffee, bag, storeOrder;

    /**
     * controller for the main menu. it has all of the button to coffee, donut etc.
     * Format a double value to a string with 2 decimal places
     * @param val the double value to format
     * @return the formatted string
     */
    @SuppressLint("DefaultLocale")
    public static String formatPrice(double val) {
        return String.format("%1$.2f", val);
    }

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeViews();
        setOnClickListeners();
    }

    /**
     * controller for the main menu. it has all of the button to coffee, donut etc.
     * Initialize the views by finding them by ID
     */
    private void initializeViews() {
        donuts = findViewById(R.id.donuts);
        coffee = findViewById(R.id.coffee);
        bag = findViewById(R.id.bag);
        storeOrder = findViewById(R.id.storeOrder);
    }

    /**
     * controller for the main menu. it has all of the button to coffee, donut etc.
     * Set the click listeners for the buttons to launch their corresponding activities
     */
    private void setOnClickListeners() {
        donuts.setOnClickListener(view -> {
            Intent donut = new Intent(MainActivity.this, OrderDonutActivity.class);
            startActivity(donut);
        });

        coffee.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, OrderCoffeeActivity.class);
            startActivity(intent);
        });

        bag.setOnClickListener(view -> {
            Intent basket = new Intent(MainActivity.this, OrderBasketActivity.class);
            startActivity(basket);
        });

        storeOrder.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, StoreOrderActivity.class);
            startActivity(intent);
        });
    }
}