package com.example.alex.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SingleTipActivity extends AppCompatActivity {

    private TextView totalBill;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_tip);

        totalBill = (TextView)findViewById(R.id.bill_amt);

        Bundle extras = getIntent().getExtras();
        double totalBillDouble = extras.getDouble("Extra__billAmount");
        String totalBillDoubleString = Double.toString(totalBillDouble);
        totalBill.setText("$ " + totalBillDoubleString);


    }
}
