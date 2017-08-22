package com.example.alex.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.Locale;

public class SplitBillActivity extends AppCompatActivity {

    final static int ONE_HUNDRED = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_bill);

        TextView billAmountTextView = (TextView)findViewById(R.id.bill_amt);
        TextView tipPercentageTextView = (TextView)findViewById(R.id.tip_percentage);
        TextView numberOfPeopleTextView = (TextView)findViewById(R.id.number_of_people);
        TextView tipEachTextView = (TextView)findViewById(R.id.tip_amount_each);
        TextView billEachTextView = (TextView)findViewById(R.id.bill_each);
        TextView remainderTextView = (TextView)findViewById(R.id.remainder);
        TextView totalPaymentTextView = (TextView)findViewById(R.id.total_payment);
        TextView totalTipTextView = (TextView)findViewById(R.id.total_tip);


        Bundle userInput = getIntent().getExtras();
        double totalBillDouble = userInput.getDouble("Extra__billAmount");
        int tipPercentageInt = userInput.getInt("Extra__tipPercentage");
        int numberOfPeopleInt = userInput.getInt("Extra__numberOfPeople");

        billAmountTextView.setText(String.format(Locale.getDefault(), "$ %.2f", totalBillDouble));
        tipPercentageTextView.setText("% " + String.valueOf(tipPercentageInt));
        numberOfPeopleTextView.setText(numberOfPeopleInt + "");

        //DecimalFormat df = new DecimalFormat("#.00");
        //df.setRoundingMode(RoundingMode.FLOOR);

        double totalTipEachDouble = (totalBillDouble * ((double)tipPercentageInt / ONE_HUNDRED)) / (double)numberOfPeopleInt;
        double totalPayEachDouble = (totalBillDouble + (totalTipEachDouble * (double)numberOfPeopleInt)) / (double)numberOfPeopleInt;

        tipEachTextView.setText(String.format(Locale.getDefault(), "%.2f", totalTipEachDouble));
        billEachTextView.setText(String.format(Locale.getDefault(), "%.2f", totalPayEachDouble));


        double totalPaymentDouble = totalPayEachDouble * (double) numberOfPeopleInt;
        double totalTipDouble = totalTipEachDouble * (double) numberOfPeopleInt;

        totalPaymentTextView.setText(String.format(Locale.getDefault(), "%.2f", totalPaymentDouble));
        totalTipTextView.setText(String.format(Locale.getDefault(), "%.2f", totalTipDouble));

        // remainderDouble is the difference between intended tip and actual tip. The difference is caused by uneven division and rounding.
        double remainderDouble = totalPaymentDouble - (totalBillDouble + totalBillDouble * ((double)tipPercentageInt / ONE_HUNDRED));
        remainderTextView.setText(String.format(Locale.getDefault(), "%.2f", remainderDouble));

    }
}
