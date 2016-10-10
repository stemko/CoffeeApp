package com.example.user11.coffeeapp;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked
     */
    public void increment(View view) {
        if (quantity == 100) {
            //Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity + 1;
        display(quantity);
    }

    /**
     * This method is called when the minus button is clicked
     */
    public void decrement(View view) {
        if (quantity == 1) {
            //Show an error message as a toast
            Toast.makeText(this, "You cannot have less coffees", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        quantity = quantity - 1;
        display(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextVIew = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextVIew.setText("" + number);
    }


//    /**
//     * This method displays the given quantity value on the screen.
//     */
//    private void displayMessage(String message) {
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_sammary_text_view);
//        orderSummaryTextView.setText(message);
//    }

    /**
     * This methos is cslled ehen the order button is called.
     */

    public void submitOrder(View view) {
        EditText text = (EditText) findViewById(R.id.name_field);
        String value = text.getText().toString();

        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        boolean haswhippedCreamCheckBox = whippedCreamCheckBox.isChecked();
        CheckBox whippedChocolateCheckBox = (CheckBox) findViewById(R.id.whipped_chocolate_checkbox);
        boolean haswhippedChocolateCheckBox = whippedChocolateCheckBox.isChecked();
        int price = calculatePrice(haswhippedCreamCheckBox, haswhippedChocolateCheckBox);
        EditText edtname = (EditText) findViewById(R.id.name_field);
        String name = edtname.getText().toString();
        String priceMessage = createOrderSummary(name, price, haswhippedCreamCheckBox, haswhippedChocolateCheckBox);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));//only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL,addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage );
        if (intent.resolveActivity(getPackageManager()) != null) ;
        {
            startActivity(intent);
        }
//        displayMessage(priceMessage);
    }

    /**
     * Calculates the price of the order.
     * return total price
     */
    private int calculatePrice(boolean haswhippedCreamCheckBox, boolean haswhippedChocolateCheckBox) {
        int price = 5;
        if (haswhippedCreamCheckBox) {
            price = price + 1;
        }
        if (haswhippedChocolateCheckBox) {
            price = price + 2;
        }
        return quantity * price;
    }

    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addWhippedChocolate) {
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream?" + addWhippedCream;
        priceMessage += "\nAdd Chocolate?" + addWhippedChocolate;
        priceMessage = priceMessage + "\nQuantity:" + quantity;
        priceMessage = priceMessage + "\nTotal: $" + price;
        priceMessage = priceMessage + "\nThank you";
        return priceMessage;
    }
}
