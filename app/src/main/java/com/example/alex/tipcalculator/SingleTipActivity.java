package com.example.alex.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;

public class SingleTipActivity extends AppCompatActivity {

    final static int ONE_HUNDRED = 100;

    private double totalBillDouble;
    private double tipPercentageDouble;
    private double tipAmountDouble;
    private double totalPaymentDouble;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_tip);

        TextView totalBill = (TextView)findViewById(R.id.bill_amt);
        TextView tipPercentage = (TextView)findViewById(R.id.tip_percentage);
        TextView tipAmount = (TextView)findViewById(R.id.tip_amount);
        TextView totalPayment = (TextView)findViewById(R.id.total_payment);


        Bundle userInput = getIntent().getExtras();
        totalBillDouble = userInput.getDouble("Extra__billAmount");
        tipPercentageDouble = userInput.getInt("Extra__tipPercentage");

        totalBill.setText(String.format(Locale.getDefault(), "$ %.2f", totalBillDouble));
        tipPercentage.setText(String.format(Locale.getDefault(), "%% %.0f", tipPercentageDouble));

        /*    This is an alternative way to get 2 decimal places in the resulting bill amount, but requires at least API 24
        DecimalFormat df = new DecimalFormat("0.00");
        String totalBillDoubleString = df.format(totalBillDouble);
        totalBill.setText(totalBillDoubleString);
        */

        tipAmountDouble = totalBillDouble * (tipPercentageDouble / ONE_HUNDRED);
        tipAmount.setText(String.format(Locale.getDefault(), "$ %.2f", tipAmountDouble));

        totalPaymentDouble = totalBillDouble + tipAmountDouble;
        totalPayment.setText(String.format(Locale.getDefault(), "$ %.2f", totalPaymentDouble));
    }
}












