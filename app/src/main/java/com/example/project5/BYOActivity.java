package com.example.project5;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project5.enums.Sauce;
import com.example.project5.enums.Size;
import com.example.project5.enums.Toppings;
import com.example.project5.pizzas.Pizza;

import java.util.ArrayList;

/**

 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */

public class BYOActivity extends AppCompatActivity {

    private Pizza buildYourOwn = PizzaMaker.createPizza("BYO");
    private Spinner pizzaSizeSpinner;
    private RadioGroup sauceRadioGroup;
    private CheckBox extraCheeseCheckBox, extraSauceCheckBox;
    private TextView priceTextView;
    private ArrayAdapter<String> availableToppingsAdapter;
    private ArrayAdapter<String> selectedToppingsAdapter;
    private ArrayList<String> availableToppings = new ArrayList<>();
    private ArrayList<String> selectedToppings = new ArrayList<>();
    private static final int MAX_TOPPINGS = 7;
    private static final int MIN_TOPPINGS = 3;
    private static final int SMALL_SELECTION = 0;

    /**
 * Overrides the {@code onCreate} method from the {@code AppCompatActivity} class.
 * This method is called when the activity is first created.
 * It initializes the activity by setting the content view to the layout defined in {@code activity_byo.xml}
 * and calls the {@code setUpViews()} method to configure the user interface for pizza customization.
 *
 * @param savedInstanceState A {@code Bundle} containing the data most recently supplied
 *                            in {@code onSaveInstanceState(Bundle)}.
 *                            Note: This parameter may be {@code null} if the activity is starting fresh.
 * @see AppCompatActivity#onCreate(Bundle)
 * @see #setContentView(int)
 * @see #setUpViews()
 */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_byo);
        setUpViews();
    }
    /**
 * Sets up the views for pizza customization, including the pizza size spinner, sauce selection,
 * toppings lists, price display, and the "Add to Order" button. This method is called during the
 * initialization of the BYOActivity.
 *
 * @see #setUpSpinner()
 * @see #setUpSauces()
 * @see #setUpToppings()
 * @see #priceTextView
 * @see #buildYourOwn
 * @see R.string#formatted_price
 * @see #addPizzaToOrder()
 */
    private void setUpViews() {
        setUpSpinner();
        setUpSauces();
        setUpToppings();
        priceTextView = findViewById(R.id.priceTextView);
        String priceString = String.format(getResources().
                getString(R.string.formatted_price), buildYourOwn.price());
        priceTextView.setText(priceString);

        Button addToOrderButton = findViewById(R.id.addToOrderButton);
        addToOrderButton.setOnClickListener(v -> addPizzaToOrder());
    }
    /**
 * Sets up the pizza size spinner, allowing the user to choose among different pizza sizes.
 * The method initializes the spinner with an array of size options (Small, Medium, Large).
 * It also configures the spinner's appearance and behavior using an ArrayAdapter and sets a
 * listener to handle size selection events.
 *
 * @see #pizzaSizeSpinner
 * @see #buildYourOwn
 * @see #handlePriceChange()
 * @see android.widget.Spinner
 * @see android.widget.ArrayAdapter
 * @see android.R.layout#simple_spinner_item
 * @see android.R.layout#simple_spinner_dropdown_item
 * @see android.widget.AdapterView.OnItemSelectedListener
 */
    private void setUpSpinner() {
        pizzaSizeSpinner = findViewById(R.id.pizzaSizeSpinner);
        String[] sizes = new String[]{"Small", "Medium", "Large"};
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sizes);
        sizeAdapter.setDropDownViewResource(android.R.layout.
                simple_spinner_dropdown_item);
        pizzaSizeSpinner.setAdapter(sizeAdapter);

        pizzaSizeSpinner.setOnItemSelectedListener(new AdapterView.
                OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String selectedSize = (String) parent.getItemAtPosition(position);
                Size size = Size.valueOf(selectedSize.toUpperCase());
                buildYourOwn.setSize(size);
                handlePriceChange();
            }
            /**
 * Callback method to be invoked when nothing is selected in the associated AdapterView.
 * This method is part of the AdapterView.OnItemSelectedListener interface.
 * In the context of the setUpSpinner method, it serves as a placeholder for handling the scenario
 * when no pizza size is selected in the pizza size spinner.
 * <p>
 * Note: The actual implementation does not include any specific action when nothing is selected,
 * and the comment indicates that no special handling is performed.
 * </p>
 *
 * @param parent The AdapterView where the selection event occurred.
 * @see AdapterView.OnItemSelectedListener#onNothingSelected(AdapterView)
 * @see #setUpSpinner()
 * @see android.widget.AdapterView
 */

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // nothing is selected
            }
        });
    }
    /**
 * Sets up the sauce selection components, including the RadioGroup for sauce options and related views.
 * This method initializes the sauceRadioGroup, sets the default selection to tomato sauce, and associates
 * an OnCheckedChangeListener to handle sauce selection events.
 *
 * @see #sauceRadioGroup
 * @see #buildYourOwn
 * @see Sauce
 * @see R.id#sauceRadioGroup
 * @see R.id#tomatoRadio
 * @see #handlePriceChange()
 * @see android.widget.RadioGroup
 * @see android.widget.RadioButton
 * @see android.widget.CompoundButton.OnCheckedChangeListener
 */
    private void setUpSauces() {
        sauceRadioGroup = findViewById(R.id.sauceRadioGroup);
        RadioButton tomatoRadio = findViewById(R.id.tomatoRadio);
        sauceRadioGroup.check(tomatoRadio.getId());
        buildYourOwn.setSauce(Sauce.TOMATO);
        sauceRadioGroup.setOnCheckedChangeListener(new RadioGroup.
                OnCheckedChangeListener() {

            /**
 * Callback method to be invoked when the checked radio button in the RadioGroup changes.
 * This method is part of the RadioGroup.OnCheckedChangeListener interface.
 * In the context of the setUpSauces method, it handles the event when the user selects a different sauce option.
 * The method updates the pizza object with the selected sauce and triggers a recalculation of the price.
 * @param group     The RadioGroup in which the checked radio button has changed.
 * @param checkedId The unique identifier of the checked radio button.
 * @see RadioGroup.OnCheckedChangeListener#onCheckedChanged(RadioGroup, int)
 * @see #setUpSauces()
 * @see #buildYourOwn
 * @see Sauce
 * @see R.id#tomatoRadio
 * @see R.id#alfredoRadio
 * @see #handlePriceChange()
 */
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.tomatoRadio) {
                    buildYourOwn.setSauce(Sauce.TOMATO);
                } else if (checkedId == R.id.alfredoRadio) {
                    buildYourOwn.setSauce(Sauce.ALFREDO);
                }
                handlePriceChange();
            }
        });

        extraCheeseCheckBox = findViewById(R.id.extraCheeseCheckBox);
        extraSauceCheckBox = findViewById(R.id.extraSauceCheckBox);
    }
    /**
 * Updates the pizza object with the selected extra options for cheese and sauce.
 * This method is called in response to user interaction with the extra cheese and extra sauce checkboxes.
 * It sets the corresponding attributes of the pizza object based on the checked status of the checkboxes
 * and triggers a recalculation of the price.
 *
 * @param view The View object that triggered this method (checkbox).
 * @see #buildYourOwn
 * @see #extraCheeseCheckBox
 * @see #extraSauceCheckBox
 * @see #handlePriceChange()
 */
    public void setExtras(View view){
        buildYourOwn.setExtraCheese(extraCheeseCheckBox.isChecked());
        buildYourOwn.setExtraSauce(extraSauceCheckBox.isChecked());
        handlePriceChange();
    }
    /**
 * Sets up the views and adapters for handling pizza toppings customization.
 * This method initializes the lists of available and selected toppings, as well as the ListViews
 * for displaying them. It also configures the HighlightArrayAdapter for each ListView and sets up
 * event listeners for the "Add Topping" and "Remove Topping" buttons.
 *
 * @see #initializeToppingsLists()
 * @see #availableToppingsListView
 * @see #selectedToppingsListView
 * @see #availableToppingsAdapter
 * @see #selectedToppingsAdapter
 * @see #setupListViewWithHighlightAdapter(ListView, ArrayAdapter)
 * @see #addToppingButton
 * @see #removeToppingButton
 * @see #handleAddTopping(ListView)
 * @see #handleRemoveTopping(ListView)
 */

    private void setUpToppings() {
        initializeToppingsLists();
        ListView availableToppingsListView = findViewById(R.id.
                availableToppingsListView);
        ListView selectedToppingsListView = findViewById(R.id.
                selectedToppingsListView);
        availableToppingsAdapter = new HighlightArrayAdapter(this,
                android.R.layout.simple_list_item_1, availableToppings);
        selectedToppingsAdapter = new HighlightArrayAdapter(this,
                android.R.layout.simple_list_item_1, selectedToppings);
        setupListViewWithHighlightAdapter(availableToppingsListView,
                availableToppingsAdapter);
        setupListViewWithHighlightAdapter(selectedToppingsListView,
                selectedToppingsAdapter);
        Button addToppingButton = findViewById(R.id.addToppingButton);
        Button removeToppingButton = findViewById(R.id.removeToppingButton);
        addToppingButton.setOnClickListener(v ->
                handleAddTopping(availableToppingsListView));
        removeToppingButton.setOnClickListener(v ->
                handleRemoveTopping(selectedToppingsListView));
    }
        /**
 * Configures a ListView with a custom HighlightArrayAdapter to handle item highlighting.
 * This method sets the provided ArrayAdapter on the specified ListView and attaches an
 * OnItemClickListener to handle item selection events. The HighlightArrayAdapter is used to
 * manage the highlighting of selected items.
 *
 * @param listView The ListView to be configured with the adapter.
 * @param adapter  The ArrayAdapter to be set on the ListView.
 * @see #listView
 * @see #adapter
 * @see HighlightArrayAdapter
 * @see android.widget.ListView
 * @see android.widget.AdapterView.OnItemClickListener
 */

    private void setupListViewWithHighlightAdapter(ListView listView,
                                                   ArrayAdapter<String> adapter) {
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) ->
                ((HighlightArrayAdapter) adapter).setSelectedPosition(position)
        );
    }

    /**
 * Initializes the lists of available toppings with values from the Toppings enum.
 * This method iterates through all values in the Toppings enum and adds their capitalized
 * and lowercased names to the list of available toppings.
 *
 * @see #availableToppings
 * @see Toppings
 * @see #capitalize(String)
 */

    private void initializeToppingsLists() {
        for (Toppings topping : Toppings.values()) {
            availableToppings.add(capitalize(topping.name().toLowerCase()));
        }
    }
        /**
 * Handles the addition of a topping to the selected toppings list.
 * This method is called when the user clicks the "Add Topping" button.
 * It checks whether the maximum number of toppings has been reached and displays a Toast message
 * if necessary. If a topping is selected, it is removed from the available toppings list, added to
 * the selected toppings list, and the UI and price are updated accordingly.
 *
 * @param listView The ListView containing the available toppings.
 * @see #selectedToppings
 * @see #availableToppings
 * @see #MAX_TOPPINGS
 * @see #availableToppingsAdapter
 * @see #buildYourOwn
 * @see #updateAdapters()
 * @see #handlePriceChange()
 * @see HighlightArrayAdapter
 * @see android.widget.ListView
 * @see android.widget.Toast
 */

    private void handleAddTopping(ListView listView) {
        if (selectedToppings.size() >= MAX_TOPPINGS) {
            Toast.makeText(this, "Cannot add more toppings. " +
                    "Maximum of 7 toppings.", Toast.LENGTH_LONG).show();
            return;
        }

        int position = listView.getCheckedItemPosition();
        if (position != ListView.INVALID_POSITION) {
            String topping = availableToppingsAdapter.getItem(position);
            Toppings toppingEnum = Toppings.valueOf(topping.toUpperCase());
            buildYourOwn.addToppings(toppingEnum);

            availableToppings.remove(topping);
            selectedToppings.add(topping);
            updateAdapters();
            listView.setItemChecked(position, false);
            handlePriceChange();
        }
        ((HighlightArrayAdapter) availableToppingsAdapter).setSelectedPosition(-1);
    }

    /**
 * Handles the removal of a topping from the selected toppings list.
 * This method is called when the user clicks the "Remove Topping" button.
 * If a topping is selected, it is removed from the selected toppings list, added back to
 * the available toppings list, and the UI and price are updated accordingly.
 *
 * @param listView The ListView containing the selected toppings.
 * @see #selectedToppings
 * @see #availableToppings
 * @see #selectedToppingsAdapter
 * @see #buildYourOwn
 * @see #updateAdapters()
 * @see #handlePriceChange()
 * @see HighlightArrayAdapter
 * @see android.widget.ListView
 */

    private void handleRemoveTopping(ListView listView) {
        int position = listView.getCheckedItemPosition();
        if (position != ListView.INVALID_POSITION) {
            String topping = selectedToppingsAdapter.getItem(position);
            Toppings toppingEnum = Toppings.valueOf(topping.toUpperCase());
            buildYourOwn.removeToppings(toppingEnum);

            selectedToppings.remove(topping);
            availableToppings.add(topping);
            updateAdapters();
            listView.setItemChecked(position, false);
            handlePriceChange();
        }
        ((HighlightArrayAdapter) selectedToppingsAdapter).setSelectedPosition(-1);
    }
    /**
 * Updates the data in the associated adapters for available and selected toppings.
 * This method notifies the adapters to update their views based on changes in the data sets.
 *
 * @see #availableToppingsAdapter
 * @see #selectedToppingsAdapter
 * @see ArrayAdapter#notifyDataSetChanged()
 */

    private void updateAdapters() {
        availableToppingsAdapter.notifyDataSetChanged();
        selectedToppingsAdapter.notifyDataSetChanged();
    }

    /**
 * Handles the change in pizza price and updates the UI accordingly.
 * This method recalculates the price of the pizza and updates the displayed price in the UI.
 *
 * @see #buildYourOwn
 * @see #priceTextView
 * @see #handlePriceChange()
 */
    private void handlePriceChange() {
        double price = buildYourOwn.price();
        String priceString = String.format(getResources().
                getString(R.string.formatted_price), price);
        priceTextView.setText(priceString);
    }

    /**
 * Adds the configured pizza to the order.
 * This method is called when the user clicks the "Add to Order" button. It checks whether
 * the minimum number of toppings is met before adding the pizza to the order. If the conditions
 * are not met, a Toast message is displayed, and the UI is not updated.
 *
 * @see #selectedToppings
 * @see #MIN_TOPPINGS
 * @see Order#getInstance()
 * @see Order#addToOrder(Pizza)
 * @see #buildYourOwn
 * @see #resetUI()
 * @see Toast
 */
    public void addPizzaToOrder() {
        if(selectedToppings.size() < MIN_TOPPINGS){
            Toast.makeText(this, "Must have minimum 3 toppings.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        Order currentOrder = Order.getInstance();
        currentOrder.addToOrder(buildYourOwn);
        Toast.makeText(this, "Pizza added to order",
                Toast.LENGTH_SHORT).show();
        resetUI();
    }    
    /**
 * Capitalizes the first letter of a given string.
 *
 * @param input The input string to be capitalized.
 * @return The capitalized string.
 * @see #capitalize(String)
 */

    public static String capitalize(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
 * Resets the user interface to its initial state.
 * This method is called after adding a pizza to the order to allow the user to customize a new pizza.
 *
 * @see #pizzaSizeSpinner
 * @see #extraCheeseCheckBox
 * @see #extraSauceCheckBox
 * @see #selectedToppings
 * @see #availableToppings
 * @see #initializeToppingsLists()
 * @see #updateAdapters()
 * @see PizzaMaker#createPizza(String)
 * @see #buildYourOwn
 * @see #handlePriceChange()
 */
    private void resetUI() {
        pizzaSizeSpinner.setSelection(SMALL_SELECTION);
        extraCheeseCheckBox.setChecked(false);
        extraSauceCheckBox.setChecked(false);

        selectedToppings.clear();
        availableToppings.clear();
        initializeToppingsLists();
        updateAdapters();

        buildYourOwn = PizzaMaker.createPizza("BYO");
        handlePriceChange();
    }

}
