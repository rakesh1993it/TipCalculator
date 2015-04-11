package com.rakesh.tipcalculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class TipCalculator extends ActionBarActivity {


         private static final String BILL_TOTAL = "BILL_TOTAL";
         private static final String CUSTOM_PERCENT = "CUSTOM_PERCENT";

         private double currentBillTotal; // bill amount entered by the user
         private int currentCustomPercent; // tip % set with the SeekBar
         private EditText tip10EditText; // displays 10% tip
         private EditText total10EditText; // displays total with 10% tip
         private EditText tip15EditText; // displays 15% tip
         private EditText total15EditText; // displays total with 15% tip
         private EditText billEditText; // accepts user input for bill total
         private EditText tip20EditText; // displays 20% tip
         private EditText total20EditText; // displays total with 20% tip
         private TextView customTipTextView; // displays custom tip percentage
         private EditText tipCustomEditText; // displays custom tip amount
         private EditText totalCustomEditText; // displays total with

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tip_calculator);

        initialize();

        if (savedInstanceState == null) {
            currentBillTotal = 0.0;
            currentCustomPercent = 18;
        } else {
            currentBillTotal = savedInstanceState.getDouble(BILL_TOTAL);
            currentCustomPercent = savedInstanceState.getInt(CUSTOM_PERCENT);
        }

        billEditText.addTextChangedListener(billEditTextWatcher);

        // get the SeekBar used to set the custom tip amount
        SeekBar customSeekBar = (SeekBar) findViewById(R.id.seekBar);
        customSeekBar.setOnSeekBarChangeListener( customseekbarListner);
    }

    private void initialize() {
        tip10EditText = (EditText) findViewById(R.id.Tip10edit);
        tip15EditText = (EditText) findViewById(R.id.Tip15edit);
        tip20EditText = (EditText) findViewById(R.id.Tip20edit);
        total10EditText = (EditText) findViewById(R.id.tot10edit);
        total15EditText = (EditText) findViewById(R.id.tot15edit);
        total20EditText = (EditText) findViewById(R.id.tot20edit);
        tipCustomEditText = (EditText) findViewById(R.id.lastTipedit);
        customTipTextView = (TextView) findViewById(R.id.customTiptext);
        totalCustomEditText = (EditText) findViewById(R.id.lasttotaledit);
        billEditText = (EditText)findViewById(R.id.billedittext);


    }

    private void updateStandard() {

        double tenPercenttip = currentBillTotal * .1;
        double tenPercentTotal = currentBillTotal + tenPercenttip;

        tip10EditText.setText(String.format("%.02f", tenPercenttip));
        total10EditText.setText(String.format("%.02f", tenPercentTotal));

        double fifteenPercenttip = currentBillTotal * .15;
        double fifteenPercentTotal = currentBillTotal + fifteenPercenttip;

        tip15EditText.setText(String.format("%.02f", fifteenPercenttip));
        total15EditText.setText(String.format("%.02f", fifteenPercentTotal));

        double twentyPercenttip = currentBillTotal * .2;
        double twentyPercentTotal = currentBillTotal + twentyPercenttip;

        tip20EditText.setText(String.format("%.02f", twentyPercenttip));
        total20EditText.setText(String.format("%.02f", twentyPercentTotal));

    }

    private void updateCustom() {

        customTipTextView.setText(currentCustomPercent + "%");
        double customTipAmount = currentBillTotal * currentCustomPercent * .01;
        double customTotalAmount = currentBillTotal + customTipAmount;

        tipCustomEditText.setText(String.format("%.02f", customTipAmount));
        totalCustomEditText.setText(String.format("%.02f", customTotalAmount));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(BILL_TOTAL, currentBillTotal);
        outState.putInt(CUSTOM_PERCENT, currentCustomPercent);
    }

    private SeekBar.OnSeekBarChangeListener customseekbarListner = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            currentCustomPercent = seekBar.getProgress();

            //seekbarPercentText.getText().toString();
            updateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

        private TextWatcher billEditTextWatcher = new TextWatcher() {


            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    currentBillTotal = Double.parseDouble(s.toString());
                } catch (NumberFormatException e) {
                    currentBillTotal = 0.0;
                }
                updateCustom();
                updateStandard();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
        };


}





