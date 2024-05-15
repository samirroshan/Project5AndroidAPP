package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project5.pizzas.Pizza;

import java.util.ArrayList;

/**
 * StoreOrdersActivity represents an activity for managing and displaying store orders.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */

public class StoreOrdersActivity extends AppCompatActivity {

    private Spinner orderSelectSpinner;
    private TextView orderTotalTextView;
    private ListView allOrdersListView;
    private ArrayAdapter<String> ordersAdapter;
    private ArrayList<String> orderNumbers;
    private ArrayList<String> orderDetails;
    private Button cancelOrderButton;
    private StoreOrders storeOrders;

    /**
     * Initializes the activity, sets up views, and populates order numbers.
     *
     * @param savedInstanceState The saved state of the activity.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        storeOrders = StoreOrders.getInstance();

        orderNumbers = new ArrayList<>();
        orderDetails = new ArrayList<>();
        setupViews();
    }

    /**
     * Sets up views, adapters, and listeners for the activity.
     */
    private void setupViews() {
        orderSelectSpinner = findViewById(R.id.orderSelectSpinner);
        orderTotalTextView = findViewById(R.id.orderTotalTextView);
        allOrdersListView = findViewById(R.id.allOrdersListView);
        cancelOrderButton = findViewById(R.id.cancelOrderButton);
        populateOrderNumbers();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, orderNumbers);
        spinnerAdapter.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        orderSelectSpinner.setAdapter(spinnerAdapter);
        ordersAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, orderDetails);
        allOrdersListView.setAdapter(ordersAdapter);
        orderSelectSpinner.setOnItemSelectedListener(new AdapterView.
                OnItemSelectedListener() {

            /**
             * Overrides the behavior when an item is selected in the order selection spinner.
             *
             * @param parent The AdapterView where the selection happened.
             * @param view The view within the AdapterView that was clicked.
             * @param position The position of the view in the adapter.
             * @param id The row id of the item that is selected.
             */
            @Override
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int position, long id) {
                String selectedOrderNumber = orderNumbers.get(position);
                displaySelectedOrder(selectedOrderNumber);
            }

            /**
             * Overrides the behavior when nothing is selected in the order selection spinner.
             *
             * @param parent The AdapterView where nothing is selected.
             */
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        cancelOrderButton.setOnClickListener(v -> cancelSelectedOrder());
        if (!orderNumbers.isEmpty()) {
            orderSelectSpinner.setSelection(0);
        }
    }
    /**
     * Populates the list of order numbers from the store's orders.
     */

    private void populateOrderNumbers() {
        for (Order order : storeOrders.getOrders()) {
            orderNumbers.add(String.valueOf(order.getOrderNum()));
        }
    }
    /**
     * Displays details of the selected order, including its pizzas and total cost.
     *
     * @param orderNumber The order number of the selected order.
     */
    private void displaySelectedOrder(String orderNumber) {
        int orderNum = Integer.parseInt(orderNumber);
        Order selectedOrder = storeOrders.getOrderById(orderNum);

        orderDetails.clear();
        for (Pizza pizza : selectedOrder.getPizzas()) {
            orderDetails.add(pizza.toString());
        }
        ordersAdapter.notifyDataSetChanged();

        orderTotalTextView.setText("$" + selectedOrder.getOrderTotal());
    }
    /**
     * Cancels the selected order, removing it from the store's order history.
     */
    private void cancelSelectedOrder() {
        int selectedPosition = orderSelectSpinner.getSelectedItemPosition();
        if (selectedPosition >= 0 && selectedPosition < orderNumbers.size()) {
            String selectedOrderNumber = orderNumbers.get(selectedPosition);
            int orderNum = Integer.parseInt(selectedOrderNumber);
            storeOrders.removeOrder(storeOrders.getOrderById(orderNum));
            orderNumbers.remove(selectedPosition);
            ((ArrayAdapter) orderSelectSpinner.getAdapter()).
                    notifyDataSetChanged();

            // update spinner selection
            if (!orderNumbers.isEmpty()) {
                int newPosition = Math.max(0, selectedPosition - 1);
                orderSelectSpinner.setSelection(newPosition);
                displaySelectedOrder(orderNumbers.get(newPosition));
            } else {
                orderDetails.clear();
                ordersAdapter.notifyDataSetChanged();
                orderTotalTextView.setText(getString(R.string.zero_placeholder));
            }
            Toast.makeText(this, "Order " + orderNum + " cancelled.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select an order to cancel.",
                    Toast.LENGTH_SHORT).show();
        }
    }
}
