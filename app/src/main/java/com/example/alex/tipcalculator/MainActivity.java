package com.example.alex.tipcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button calculateButton;
    EditText billAmount;
    double billAmountDouble;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculateButton = (Button)findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View V) {
                Bundle billAmountBundle = new Bundle();

                billAmount = (EditText)findViewById(R.id.bill_amt);
                billAmountDouble = Double.parseDouble(billAmount.getText().toString());

                Intent intent = new Intent(MainActivity.this, SingleTipActivity.class);
                billAmountBundle.putDouble("Extra__billAmount", billAmountDouble);

                intent.putExtras(billAmountBundle);
                startActivity(intent);
            }
        });
    }
}
