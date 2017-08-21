package com.example.alex.tipcalculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

        calculateButton = (Button)findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View V) {
                try {
                    Bundle userInput = new Bundle();

                    billAmount = (EditText) findViewById(R.id.bill_amt);
                    billAmountDouble = Double.parseDouble(billAmount.getText().toString());

                    Intent intent = new Intent(MainActivity.this, SingleTipActivity.class);
                    userInput.putDouble("Extra__billAmount", billAmountDouble);
                    userInput.putInt("Extra__tipPercentage", seekBar.getProgress());

                    intent.putExtras(userInput);
                    startActivity(intent);
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "WRONG FORMAT FUCK YOU", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

}
