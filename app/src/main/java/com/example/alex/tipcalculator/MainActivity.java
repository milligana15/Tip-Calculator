package com.example.alex.tipcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static int SEEKBAR_MAX = 40;
    final static int SEEKBAR_STARTING_PROGRESS = 20;

    Button calculateButton;
    EditText billAmount;
    double billAmountDouble;
    SeekBar seekBar;
    TextView tipPercentage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        seekBar = (SeekBar)findViewById(R.id.tip_percentage_seekbar);
        seekBar.setMax(SEEKBAR_MAX);
        seekBar.setProgress(SEEKBAR_STARTING_PROGRESS);

        tipPercentage = (TextView)findViewById(R.id.tip_percentage);
        tipPercentage.setText(seekBar.getProgress() + " %");

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tipPercentage.setText(seekBar.getProgress() + " %");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        final CheckBox splitBillCheckBox = (CheckBox)findViewById(R.id.split_tip_checkBox);
        final EditText numberOfPeople = (EditText)findViewById(R.id.number_of_people);
        numberOfPeople.setVisibility(View.INVISIBLE);

        splitBillCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (splitBillCheckBox.isChecked()) {
                    numberOfPeople.setVisibility(View.VISIBLE);
                }
                else {
                    numberOfPeople.setVisibility(View.INVISIBLE);
                }
            }
        });

        calculateButton = (Button)findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View V) {
                try {
                    Bundle userInput = new Bundle();

                    billAmount = (EditText) findViewById(R.id.bill_amt);
                    billAmountDouble = Double.parseDouble(billAmount.getText().toString());

                    Intent intent = new Intent(MainActivity.this, SingleTipActivity.class);
                    userInput.putDouble("Extra__billAmount", billAmountDouble);
                    userInput.putInt("Extra__tipPercentage", seekBar.getProgress());

                    intent.putExtras(userInput);
                    if (!splitBillCheckBox.isChecked()) {
                        startActivity(intent);
                    }
                    else {
                        int numberOfPeopleInt = Integer.parseInt(numberOfPeople.getText().toString());
                        userInput.putInt("Extra__numberOfPeople", numberOfPeopleInt);

                        Intent intentSplit = new Intent(MainActivity.this, SplitBillActivity.class);
                        intentSplit.putExtras(userInput);
                        startActivity(intentSplit);
                    }
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Oops! Forgetting something...?", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    //TODO butt-stuff  3

}
