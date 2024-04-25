package com.example.project5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * controller for activity_donut_order
 * * @author Sharukh Khan, Hamad Naveed
 */
public class OrderDonutActivity extends AppCompatActivity {
    public static final int MAX_SIZE_OF_FLAVORS = 12;
    private Button addToOrder;
    private TextView subTotal;
    private static ArrayList<Donut> items = new ArrayList<>();
    private static final int[] ITEM_IMAGES = {R.drawable.creamyglazed, R.drawable.jellyfilled, R.drawable.chocolatefrosted,
            R.drawable.strawberryfrosted, R.drawable.originalsugar, R.drawable.lemonfilled, R.drawable.cinnamonsugar,
            R.drawable.blueberryblues, R.drawable.oldfashion, R.drawable.creamyglazedholes, R.drawable.jellyfilledholes, R.drawable.cinnamonsugarholes};

    private DonutTypeAdapter donutAdapter;

    /**
     Initializes the Donut Order activity by setting up the RecyclerView and button views.
     Populates the list of available donut types if it is empty.
     Sets up the DonutTypeAdapter and sets it to the RecyclerView.
     Handles the onClickListener for the "Add to Order" button.
     Displays a dialog asking the user to confirm their order before adding it to the OrderBasketActivity.
     */
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donuts_order);

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        subTotal = findViewById(R.id.subTotal);
        addToOrder = findViewById(R.id.addToOrder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (items.isEmpty()) {
            String[] itemNames = getResources().getStringArray(R.array.itemNames);
            for (int i = 0; i < itemNames.length; i++) {
                items.add(new Donut(itemNames[i], ITEM_IMAGES[i]));
            }
        }
        if (donutAdapter == null || donutAdapter.getItemCount() != items.size()) {
            donutAdapter = new DonutTypeAdapter(this, items);
            recyclerView.setAdapter(donutAdapter);
        }
        addToOrder.setOnClickListener(view -> {
            Collection<Donut> zeroItems = items.stream().filter(d -> d.getAmount()==0).map(Donut::new).collect(Collectors.toList());
            if(zeroItems.size() == MAX_SIZE_OF_FLAVORS) {
                Toast.makeText(view.getContext(), "No donut selected \uD83D\uDE14", Toast.LENGTH_LONG).show();
                subTotal.setText("0.00");
                return;
            }
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("Adding to bag \uD83D\uDC5C");
            alert.setMessage("Are you sure you want to add this item?");
            alert.setPositiveButton("yes", (dialog, which) -> {
                Collection<Donut> nonZeroItems = items.stream().filter(d -> d.getAmount()>0).map(Donut::new).collect(Collectors.toList());
                OrderBasketActivity.addToOrder(nonZeroItems);
                donutAdapter.reset();
                Toast.makeText(view.getContext(), "Order added to \uD83D\uDC5C", Toast.LENGTH_SHORT).show();
            }).setNegativeButton("no", (dialog, which) -> Toast.makeText(view.getContext(), "order not placed \uD83D\uDE14", Toast.LENGTH_LONG).show());
            AlertDialog dialog = alert.create();
            dialog.show();
        });
    }

    /**
     Calculates the total cost of all items in the list of menu items,
     and sets the text of the subTotal TextView to display the formatted total price.
     */
    public void newTotal()
    {
        double totalPrice =  items.stream().mapToDouble(MenuItem::getCost).sum();
        subTotal.setText(MainActivity.formatPrice(totalPrice));
    }
}