/**
 * The `CurrentOrderActivity` class represents the activity displaying the current order details.
 * It allows users to view the pizzas in the current order, place the order, and remove pizzas from the order.
 *
 * @see AppCompatActivity
 * @see Order
 * @see StoreOrders
 * @see HighlightArrayAdapter
 */
package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project5.pizzas.Pizza;

import java.util.ArrayList;

/**

 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */

public class CurrentOrderActivity extends AppCompatActivity {

    private ListView pizzaListView;
    private TextView orderNumberTextView, subTotalTextView,
            salesTaxTextView, orderTotalTextView;
    private Button placeOrderButton, removePizzaButton;
    private ArrayAdapter<String> pizzaAdapter;
    private ArrayList<String> pizzaList;
    private static final double SALES_TAX_RATE = 0.06625;
    private static Order order = Order.getInstance();
    private static StoreOrders storeOrders = StoreOrders.getInstance();

    /**
     * Called when the activity is first created. Initializes the views and sets up the initial state.
     *
     * @param savedInstanceState A Bundle containing the activity's previously saved state, if available.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        pizzaList = new ArrayList<>();

        setUpViews();
        updateOrderDisplay();
    }
    /**
     * Sets up the views for the activity, including text views, buttons, and the pizza list view.
     * Also registers click listeners for the place order and remove pizza buttons.
     */
    private void setUpViews() {
        orderNumberTextView = findViewById(R.id.orderNumberTextView);
        subTotalTextView = findViewById(R.id.subTotalTextView);
        salesTaxTextView = findViewById(R.id.salesTaxTextView);
        orderTotalTextView = findViewById(R.id.orderTotalTextView);
        pizzaListView = findViewById(R.id.pizzaListView);
        placeOrderButton = findViewById(R.id.placeOrderButton);
        removePizzaButton = findViewById(R.id.removePizzaButton);
        pizzaAdapter = new HighlightArrayAdapter(this,
                android.R.layout.simple_list_item_1, pizzaList);
        pizzaListView.setAdapter(pizzaAdapter);
        pizzaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * Overrides the default behavior for item clicks in the pizza list view.
             *
             * @param parent   The AdapterView where the click happened.
             * @param view     The view within the AdapterView that was clicked.
             * @param position The position of the view in the adapter.
             * @param id       The row id of the item that was clicked.
             */
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                ((HighlightArrayAdapter)pizzaAdapter).
                        setSelectedPosition(position);
            }
        });

        /**
         * Sets up the click listener for the "Place Order" button, defining the behavior when the button is clicked.
         *
         * @see View.OnClickListener#onClick(View)
         */
        placeOrderButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Called when the "Place Order" button is clicked. Invokes the {@link #handlePlaceOrder()} method.
             *
             * @param v The view that was clicked.
             * @see View.OnClickListener#onClick(View)
             */
            @Override
            public void onClick(View v) {
                handlePlaceOrder();
            }
        });

        /**
         * Sets up the click listener for the "Remove Pizza" button, defining the behavior when the button is clicked.
         *
         * @see View.OnClickListener#onClick(View)
         */
        removePizzaButton.setOnClickListener(new View.OnClickListener() {

            /**
             * Called when the "Remove Pizza" button is clicked. Invokes the {@link #handleRemovePizza()} method.
             *
             * @param v The view that was clicked.
             * @see View.OnClickListener#onClick(View)
             */
            @Override
            public void onClick(View v) {
                handleRemovePizza();
            }
        });
    }

    /**
     * Handles the place order action. Checks if the order is not empty, then adds the order to the store
     * and creates a new order for further customization. Displays a toast with the order number.
     */
    public void handlePlaceOrder() {
        if (pizzaList.isEmpty()) {
            Toast.makeText(this, "No pizzas in the order. Please add " +
                    "pizzas before placing the order.", Toast.LENGTH_LONG).show();
            return;
        }
        int orderNumber = order.getOrderNum();
        storeOrders.addOrder(order);
        Order.createNewOrder();
        order = Order.getInstance();
        SpecialtyActivity.setOrder(order);
        Toast.makeText(this, "Order successfully placed. Your order number " +
                "is " + orderNumber + ".", Toast.LENGTH_LONG).show();
        updateOrderDisplay();
    }
    /**
     * Handles the remove pizza action. Removes the selected pizza from the order and updates the UI.
     */
    public void handleRemovePizza() {
        int selectedPosition = ((HighlightArrayAdapter)
                pizzaAdapter).getSelectedPosition();
        if (selectedPosition >= 0 && selectedPosition < pizzaList.size()) {
            pizzaList.remove(selectedPosition);
            order.removePizza(selectedPosition);
            ((HighlightArrayAdapter) pizzaAdapter).setSelectedPosition(-1);
            pizzaAdapter.notifyDataSetChanged();
            calculateTotals(order.getPizzas());
            Toast.makeText(this, "Pizza removed from order.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select a pizza to remove.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Updates the order display by clearing and re-populating the pizza list and updating the total costs.
     */

    private void updateOrderDisplay() {
        pizzaAdapter.clear();
        pizzaAdapter.addAll(pizzaList);
        pizzaAdapter.notifyDataSetChanged();
        if (order != null) {
            orderNumberTextView.setText(String.valueOf(order.getOrderNum()));
            ArrayList<Pizza> pizzaItems =  order.getPizzas();
            for(Pizza pizza : pizzaItems){
                if(pizza != null) {
                    pizzaList.add(pizza.toString());
                }
            }
        }
        calculateTotals(order.getPizzas());
    }
    /**
     * Calculates and displays the subtotal, sales tax, and total cost based on the provided pizza items.
     *
     * @param pizzaItems The list of pizzas in the order.
     */

    private void calculateTotals(ArrayList<Pizza> pizzaItems) {
        double subTotal = 0;
        for(Pizza pizza : pizzaItems){
            if(pizza != null) {
                subTotal += pizza.price();
            }
        }
        double salesTax = Math.round((subTotal*SALES_TAX_RATE)*100.0)/100.0;
        double totalCost = Math.round((subTotal + salesTax)*100.0)/100.0;
        String orderNumString = Integer.toString(order.getOrderNum());
        String subTotalString = String.format(getResources().
                getString(R.string.formatted_price), subTotal);
        String salesTaxString = String.format(getResources().
                getString(R.string.formatted_price), salesTax);
        String totalString = String.format(getResources().
                getString(R.string.formatted_price), totalCost);
        orderTotalTextView.setText(orderNumString);
        subTotalTextView.setText(subTotalString);
        salesTaxTextView.setText(salesTaxString);
        orderTotalTextView.setText(totalString);
    }

}
