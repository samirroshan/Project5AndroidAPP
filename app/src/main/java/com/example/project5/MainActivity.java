package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * The main activity that serves as the entry point of the application.
 * <p>This activity provides options to navigate to different features of the pizza ordering application.
 * It allows users to create their own pizza, explore specialty pizzas, view the current order, and see
 * the store orders.
 * @author Peter Lee
 * @author Samir Roshan
 * @author Anvin Thomas
 */
public class MainActivity extends AppCompatActivity {

/**
 * Called when the activity is starting.
 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Displays the "Build Your Own" (BYO) activity when the corresponding button is clicked.
     *
     * @param view The view that was clicked.
     */
    public void displayBYOActivity(View view){
        Intent intent = new Intent(MainActivity.this, BYOActivity.class);
        startActivity(intent);
    }

    /**
     * Displays the "Specialty" activity when the corresponding button is clicked.
     *
     * @param view The view that was clicked.
     */

    public void displaySpecialtyActivity(View view){
        Intent intent = new Intent(MainActivity.this, SpecialtyActivity.class);
        startActivity(intent);
    }
    /**
     * Displays the "Current Order" activity when the corresponding button is clicked.
     *
     * @param view The view that was clicked.
     */

    public void displayCurrentOrderActivity(View view){
        Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
        startActivity(intent);
    }
    /**
     * Displays the "Store Orders" activity when the corresponding button is clicked.
     *
     * @param view The view that was clicked.
     */

    public void displayStoreOrdersActivity(View view){
        Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
        startActivity(intent);
    }


}
