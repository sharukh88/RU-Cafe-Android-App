package com.example.project5;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * controller for activity_coffee_order
 * * @author Sharukh Khan, Hamad Naveed
 */
public class OrderCoffeeActivity extends AppCompatActivity {
    private Spinner size;
    private CheckBox sweetCream, frenchVanilla, irishCream, caramel, mocha;
    private Spinner quantity;
    private TextView subTotal;
    private Button addToOrder;
    private static Coffee coffee;
    public static final int MAX_QUANTITY = 13;

    /**
     This method creates a list of integers representing the quantity of items that can be added to an order.
     The maximum quantity of items that can be added to an order is set as a constant called MAX_QUANTITY.
     @return The list of integer values representing the quantity of items that can be added to an order.
     */
    public List<Integer> amountList()
    {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < MAX_QUANTITY; i++)
        {
            list.add(i);
        }
        return list;
    }

    /**
     * Returns a list of available coffee sizes as strings.
     * @return List of available coffee sizes
     */
    public List<String> sizeList()
    {
        return new ArrayList<>(Arrays.asList(Coffee.SIZES));
    }

    /**
     * Sets up the UI elements and listeners for the activity.
     * Populates the spinners for size and quantity, sets the adapters,
     * and adds listeners for item selection.
     * Adds a listener for the Add to Order button to create a new Coffee object,
     * add it to the order, and display a confirmation dialog.
     */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_order);
        views();
        size.setAdapter(new ArrayAdapter<>(this, R.layout.support_spinner, sizeList()));
        quantity.setAdapter(new ArrayAdapter<>(this, R.layout.spinner_dropdown, amountList()));

        size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectSize();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectAmount();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        addToOrder.setOnClickListener(view -> {
            if(quantity.getSelectedItem().equals(0)) {
                Toast.makeText(view.getContext(), "No coffee selected \uD83D\uDE14", Toast.LENGTH_LONG).show();
                reset();
                subTotal.setText("0.00");
                return;
            }
            AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
            alert.setTitle("Adding to bag \uD83D\uDC5C");
            alert.setMessage("Are you sure you want to add this item to bag?");
            alert.setPositiveButton("yes", (dialog, which) -> {
                OrderBasketActivity.addToOrder(new Coffee(coffee));
                reset();
                Toast.makeText(view.getContext(), " Order has been added to \uD83D\uDC5C", Toast.LENGTH_SHORT).show();
            }).setNegativeButton("no", (dialog, which) -> Toast.makeText(view.getContext(), " Order has not been placed", Toast.LENGTH_LONG).show());
            AlertDialog dialog = alert.create();
            dialog.show();
        });
    }

    /**
     Sets the selected size of coffee to the Coffee object and updates the subtotal accordingly.
     If the size spinner is null, returns without doing anything.
     */
    void selectSize() {
        if (size == null) return;
        coffee.setSize((String) size.getSelectedItem());
        subTotal.setText(coffee.getCostText());
    }

    /**
     Resets the coffee order form to default values.
     Creates a new instance of Coffee object.
     Sets the quantity and size selection to the first option.
     Sets the subTotal TextView to the default cost of the coffee.
     Unchecks all the checkboxes for additional flavors.
     */
    public void reset() {
        coffee = new Coffee();
        quantity.setSelection(0);
        size.setSelection(0);
        subTotal.setText(coffee.getCostText());

        for (CheckBox checkBox : new CheckBox[]{sweetCream, frenchVanilla, irishCream, caramel, mocha}) {
            checkBox.setChecked(false);
        }
    }

    /**
     Sets the selected size of coffee to the Coffee object and updates the subtotal accordingly.
     If the size spinner is null, returns without doing anything.
     */
    void selectAmount() {
        if (quantity == null) return;
        coffee.setAmount((Integer) quantity.getSelectedItem());
        subTotal.setText(coffee.getCostText());
    }

    /**
     Initializes the views and sets up the Coffee object and subTotal text to display the initial cost.
     Also initializes all the checkboxes for sweet cream, French vanilla, Irish cream, caramel and mocha flavors.
     */
    private void views() {
        coffee = new Coffee();
        size = findViewById(R.id.size);
        quantity = findViewById(R.id.quantitiy);
        subTotal = findViewById(R.id.cSubTotal);
        addToOrder = findViewById(R.id.addCToOrder);
        sweetCream = findViewById(R.id.sweetCream);
        frenchVanilla = findViewById(R.id.frenchVanilla);
        irishCream = findViewById(R.id.irishCream);
        caramel = findViewById(R.id.caramel);
        mocha = findViewById(R.id.mocha);
        subTotal.setText(coffee.getCostText());
    }

    /**
     This method is called when a checkbox is checked or unchecked.
     It retrieves the id of the checkbox and adds or removes the corresponding text from the coffee order
     based on whether the checkbox is checked or unchecked. It then updates the subtotal displayed on the screen.
     @param view The checkbox that was checked or unchecked.
     */
    public void checkBox(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        if (view.getId() == R.id.frenchVanilla) {
            if (checked) {
                coffee.add(((CheckBox) view).getText());
            } else {
                coffee.remove(((CheckBox) view).getText());
            }
        } else if (view.getId() == R.id.caramel) {
            if (checked) {
                coffee.add(((CheckBox) view).getText());
            } else {
                coffee.remove(((CheckBox) view).getText());
            }
        } else if (view.getId() == R.id.irishCream) {
            if (checked) {
                coffee.add(((CheckBox) view).getText());
            } else {
                coffee.remove(((CheckBox) view).getText());
            }
        } else if (view.getId() == R.id.mocha) {
            if (checked) {
                coffee.add(((CheckBox) view).getText());
            } else {
                coffee.remove(((CheckBox) view).getText());
            }
        } else if (view.getId() == R.id.sweetCream) {
            if (checked) {
                coffee.add(((CheckBox) view).getText());
            } else {
                coffee.remove(((CheckBox) view).getText());
            }
        }
        subTotal.setText(coffee.getCostText());
    }
}
