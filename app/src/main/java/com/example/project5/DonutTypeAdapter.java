package com.example.project5;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter class for OrderDonutActivity.
 * works with recycler view etc.
 * * @author Sharukh Khan, Hamad Naveed
 */

public class DonutTypeAdapter extends RecyclerView.Adapter<DonutTypeAdapter.Holder> {

    /**
     * contructor for DonutTypeAdapter
     * @param context
     * @param items
     */
    public DonutTypeAdapter(OrderDonutActivity context, ArrayList<Donut> items) {
        this.c = context;
        this.items = items;
    }
    private final OrderDonutActivity c;

    /**
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     * @return
     */
    @NonNull
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.recycle_view, parent, false);
        return new Holder(view);
    }

    private final ArrayList<Donut> items;

    /**
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        List<Integer> list = createQuantityList();

        Donut item = items.get(position);
        bindDonutData(holder, item);
        bindQuantitySpinner(holder, item, list);
        bindQuantityListener(holder, item);

        holders.add(holder);
    }

    public static final int maxSize = 13;
    private List<Integer> createQuantityList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < maxSize; i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * holder value for items and donut
     * @param holder
     * @param item
     */
    @SuppressLint("SetTextI18n")
    private void bindDonutData(Holder holder, Donut item) {
        holder.name.setText(item.getDonutFlavor());
        holder.price.setText(Double.toString(item.getUnitAmount()));
        holder.item.setImageResource(item.getImage());
    }

    /**
     Binds the spinner for the quantity of the selected donut item.
     @param holder The view holder for the selected donut item.
     @param item The selected donut item.
     @param list The list of quantity values for the spinner.
     */
    private void bindQuantitySpinner(Holder holder, Donut item, List<Integer> list) {
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(c, R.layout.support_spinner, list);
        holder.amount.setAdapter(adapter);
        holder.amount.setSelection(item.getAmount());
    }

    /**
     Binds the quantity listener for the spinner.
     @param holder The view holder for the selected donut item.
     @param item The selected donut item.
     */
    private void bindQuantityListener(Holder holder, Donut item) {
        holder.amount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Integer qty = (Integer) holder.amount.getSelectedItem();
                item.setQuantity(qty);
                c.newTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    /**
     * reset donut amount and total
     */
    public void reset() {
        resetDonutQuantities();
        resetAmountSpinners();
        updateTotal();
    }

    /**
     * reset donut quantitiy
     */
    private void resetDonutQuantities() {
        for (Donut donut : items) {
            donut.setQuantity(0);
        }
    }

    private final ArrayList<Holder> holders = new ArrayList<>();
    private void resetAmountSpinners() {
        for (Holder holder : holders) {
            holder.amount.setSelection(0);
        }
    }

    /**
     * update total amount
     */
    private void updateTotal() {
        c.newTotal();
    }

    /**
     * count item
     */
    @Override
    public int getItemCount() {
        return items.size();
    }

    /**
     * holds references to the views used to display a Donut object in the RecyclerView.
     */
    public static class Holder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView price;
        private final ImageView item;
        private final Spinner amount;

        public Holder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.flavor);
            price = itemView.findViewById(R.id.price);
            amount = itemView.findViewById(R.id.amount);
            item = itemView.findViewById(R.id.item);
        }
    }
}